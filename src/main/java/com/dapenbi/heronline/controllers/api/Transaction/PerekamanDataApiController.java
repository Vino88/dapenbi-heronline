package com.dapenbi.heronline.controllers.api.Transaction;


import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.CounterRequest;
import com.dapenbi.heronline.dto.HerRegistrasiDetailRequest;
import com.dapenbi.heronline.dto.MPensiunDto;
import com.dapenbi.heronline.dto.RoleDto;
import com.dapenbi.heronline.dto.component.Select2Default;
import com.dapenbi.heronline.models.*;
import com.dapenbi.heronline.repository.CounterRepository;
import com.dapenbi.heronline.repository.MpensiunRepository;
import com.dapenbi.heronline.service.PerekamanDataService;
import com.dapenbi.heronline.util.MessageUtil;
import com.dapenbi.heronline.util.ReportCompiler;
import com.dapenbi.heronline.util.ReportUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PerekamanDataApiController {

    @Autowired
    PerekamanDataService perekamanDataService;
    @Autowired
    CounterRepository counterRepository;

    @Autowired
    MpensiunRepository mpensiunRepository;

    private DataSource dataSource;

    public PerekamanDataApiController(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @GetMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA)
    public Page<PerekamanDataList> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        String srcParam = searchParam;
        if (searchParam != ""){
            srcParam = "%"+searchParam+"%";
        }
        Page<PerekamanDataList> aIt = perekamanDataService.getPagingAllDataList(srcParam,pageRequest);
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA+"/counter-add")
    public ResponseEntity<MessageUtil> save(@ModelAttribute CounterRequest requestData){
        Counter counter = new Counter();
        counter.setName(requestData.getName());
        counter.setFingerId(requestData.getFingerId());
        counter.setUserId(requestData.getUserId());
        counter.setStatus(requestData.getStatus());
        MessageUtil messageUtil;
        try {
            counterRepository.save(counter);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);

    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_PEREKAMAN_DATA+"/search-id-perekaman-data")
    public List<Select2Default> select2Api(Pageable pageable, @RequestParam(name = "searchParam",required = false)String q){
        List<Select2Default>select2DefaultList = new ArrayList<>();
        Iterable<Mpensiun> mPensiun_ = mpensiunRepository.findAllByNip(q, pageable);
        if (((Page<Mpensiun>) mPensiun_).getTotalPages() > 0){
            mPensiun_.forEach((k)->{
                MPensiunDto dto = k.toDto();
                select2DefaultList.add(new Select2Default(String.valueOf(k.getIdPenerimaMP()),k.getIdPenerimaMP() + " - " +k.getNamaPenerimaMp(),dto));
            });
        }else if (q == "" || q == null){
            Iterable<Mpensiun> mPensiuns = mpensiunRepository.getAllLimit();
            mPensiuns.forEach((k)->{
                MPensiunDto dto = k.toDto();
                select2DefaultList.add(new Select2Default(String.valueOf(k.getIdPenerimaMP()),k.getIdPenerimaMP() + " - " +k.getNamaPenerimaMp(),dto));
            });
        }
        return select2DefaultList;
    }

    @GetMapping(Prefix.PATH_API + Prefix.PATH_TRANSACTION + Prefix.PATH_PEREKAMAN_DATA +"/report-informasi")
    public void report(HttpServletResponse httpServletResponse, @RequestParam("tipeReport") String tipeReport, @RequestParam(name="nip", required = false) String nip, @RequestParam(name="download", required = false) Boolean download){
        String jasperFileName = null;
        Map<String, Object> parameters = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

      //  String originalInput = password;
      //  String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
      //  byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
      //  String decodedString = new String(decodedBytes);

        try {
            switch (tipeReport) {

                case "informasi" :
                    jasperFileName = "DataInformasiHerRegistrasi";
                    parameters = new HashMap<>();
                    parameters.put("nip", nip);
                    parameters.put("barcode","/static/images/qrcode.png");
                    break;
            }
            if (jasperFileName != null) {
                createReport(httpServletResponse, nip, download, parameters, jasperFileName);
            }

        } catch (IOException | JRException e1) {
            e1.printStackTrace();
        }
    }

    private void createReport(HttpServletResponse httpServletResponse, String nip, Boolean download, Map<String, Object> parameters, String jasperFileName) throws JRException, IOException {
        ReportCompiler rc = new ReportCompiler();
        JasperReport jasperReport = rc.compileReport(jasperFileName);
        setReportHeader(httpServletResponse, nip, jasperFileName, download);

        ReportUtil reportUtil = new ReportUtil(jasperFileName, httpServletResponse.getOutputStream());
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
    }

    private void setReportHeader(HttpServletResponse httpServletResponse, String nip, String jasperFileName, Boolean download) {
        // Set web response output stream
        MediaType mediaType = MediaType.parseMediaType("application/pdf");
        httpServletResponse.setContentType(mediaType.getType());
        if ((download != null) && (download)) {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + jasperFileName + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + nip + ".pdf");
        } else {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/pdf");
        }
    }
}
