package com.dapenbi.heronline.controllers.api.report;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.util.ReportCompiler;
import com.dapenbi.heronline.util.ReportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportBelumRegistrasiApiController {

    private DataSource dataSource;

    public ReportBelumRegistrasiApiController(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @GetMapping(Prefix.PATH_API + Prefix.PATH_REPORT + Prefix.PATH_BELUM_HER+ "/report-belum-her")
    public void report(HttpServletResponse httpServletResponse, @RequestParam("tipeReport") String tipeReport, @RequestParam(name="tahunHer", required = false) String tahunHer, @RequestParam(name="download", required = false) Boolean download){
        String jasperFileName = null;
        Map<String, Object> parameters = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

        try {

            switch (tipeReport) {
                case "belum_her" :

                    jasperFileName = "LaporanRegistrasiPenerimaManfaatPensiunYangBelumRegistrasi";
                    parameters = new HashMap<>();
                    parameters.put("tahun_her",tahunHer);
                    break;
            }
            if (jasperFileName != null) {
                createReport(httpServletResponse, tahunHer, download, parameters, jasperFileName);
            }

        } catch (IOException | JRException e1) {
            e1.printStackTrace();
        }
    }

    private void createReport(HttpServletResponse httpServletResponse, String tahunHer, Boolean download, Map<String, Object> parameters, String jasperFileName) throws JRException, IOException {
        ReportCompiler rc = new ReportCompiler();
        JasperReport jasperReport = rc.compileReport(jasperFileName);
        setReportHeader(httpServletResponse, tahunHer, jasperFileName, download);
        // Set report
        ReportUtil reportUtil = new ReportUtil(jasperFileName, httpServletResponse.getOutputStream());
        try{
            Connection conn = DataSourceUtils.getConnection(dataSource);
            reportUtil.setReport(parameters, conn, new JRPdfExporter());
            SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);
            reportUtil.getExporter().setConfiguration(reportConfig);
            // write report to output stream
            reportUtil.getExporter().exportReport();
            reportUtil.getOutputStream().flush();
            reportUtil.getOutputStream().close();
        }catch( JRException | IOException e){
            e.printStackTrace();
        }
    }

    private void setReportHeader(HttpServletResponse httpServletResponse, String tahunHer, String jasperFileName, Boolean download) {
        // Set web response output stream
        MediaType mediaType = MediaType.parseMediaType("application/pdf");
        httpServletResponse.setContentType(mediaType.getType());
        if ((download != null) && (download)) {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + jasperFileName + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + tahunHer + ".pdf");
        } else {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/pdf");
        }
    }
}
