package com.dapenbi.heronline.controllers.api.Transaction;

import com.dapenbi.heronline.Prefix;
import com.dapenbi.heronline.dto.*;
import com.dapenbi.heronline.dto.component.Select2Default;
import com.dapenbi.heronline.models.*;
import com.dapenbi.heronline.models.CompositeId.HerRegistrasiDetailPK;
import com.dapenbi.heronline.repository.*;
import com.dapenbi.heronline.service.HerRegistrasiService;
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
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RegistrasiUlangApiController {

    @Autowired
    HerRegistrasiService herRegistrasiService;
    @Autowired
    HerRegistrasiDetailRepository herRegistrasiDetailRepository;
    @Autowired
    ProvinsiRepository provinsiRepository;
    @Autowired
    KotaRepository kotaRepository;
    @Autowired
    KecamatanRepository kecamatanRepository;
    @Autowired
    KelurahanRepository kelurahanRepository;

    private DataSource dataSource;

    public RegistrasiUlangApiController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG+"/{tahunHer}")
    public Page<HerRegistrasiDetail> index(Pageable pageRequest, @PathVariable(name = "tahunHer") String tahunHer, @RequestParam(name = "searchParam", required = false) String searchParam){
        String srcParam = searchParam;
        if (searchParam != ""){
            srcParam = searchParam;
        }
        Page<HerRegistrasiDetail> aIt = herRegistrasiService.findByTahunHer(tahunHer,srcParam,pageRequest);
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG)
    public ResponseEntity<MessageUtil> save(@ModelAttribute HerRegistrasiDetailRequest requestData){
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        HerRegistrasiDetail datatemp = herRegistrasiDetailRepository.findById_TahunHerAndId_Nip(requestData.getTahunHer(),requestData.getNip());
        datatemp.setId(new HerRegistrasiDetailPK(requestData.getTahunHer(),requestData.getNip()));
        datatemp.setNomorHer(requestData.getNomorHer());
        datatemp.setNamaPeserta(requestData.getNamaPeserta());
        datatemp.setNipPas1Her(requestData.getNipPas1Her());
        datatemp.setNipPas2Her(requestData.getNipPas2Her());
        datatemp.setJenisKelaminPas1Her(requestData.getJenisKelaminPas1Her());
        datatemp.setJenisKelaminPas2Her(requestData.getJenisKelaminPas2Her());
        datatemp.setNamaPenerimaHer(requestData.getNamaPenerimaHer());
        try {
            datatemp.setTglHer(formatter1.parse(requestData.getNamaPenerimaHer()));
        }catch (ParseException e) {
            datatemp.setTglHer(null);
        }
        try {
            datatemp.setTglLahirPenerimaHer(formatter1.parse(requestData.getTglLahirPenerimaHer()));
        } catch (ParseException e) {
            datatemp.setTglLahirPenerimaHer(null);
        }
        try {
            datatemp.setTglMeninggalPenerimaHer(formatter1.parse(requestData.getTglMeninggalPenerimaHer()));
        } catch (ParseException e) {
            datatemp.setTglMeninggalPenerimaHer(null);
        }
        datatemp.setIdSuskelHer(Long.valueOf(requestData.getIdSuskelHer()));
        datatemp.setNamaSuskelHer(requestData.getNamaSuskelHer());
        datatemp.setIdKantorBayarHer(Long.valueOf(requestData.getIdKantorBayarHer()));
        datatemp.setNamaKantorBayarHer(requestData.getNamaKantorBayarHer());
        datatemp.setAlamatIsDalamNegeriHer(requestData.getAlamatIsDalamNegeriHer());
        datatemp.setAlamatLengkapHer(requestData.getAlamatLengkapHer());
        datatemp.setRtHer(requestData.getRtHer());
        datatemp.setRwHer(requestData.getRwHer());
        datatemp.setIdKelurahanHer(Long.valueOf(requestData.getIdKelurahanHer()));
        datatemp.setNamaKelurahanHer(requestData.getNamaKelurahanHer());
        datatemp.setIdKecamatanHer(Long.valueOf(requestData.getIdKecamatanHer()));
        datatemp.setNamaKecamatanHer(requestData.getNamaKecamatanHer());
        try {
            datatemp.setIdKotaKabHer(Long.valueOf(requestData.getIdKotaKabHer()));
        }catch (Exception ex){
            datatemp.setIdKotaKabHer(null);
        }
        datatemp.setNamaKotaKabHer(requestData.getNamaKotaKabHer());
        try {
            datatemp.setIdProvinsiHer(Long.valueOf(requestData.getIdProvinsiHer()));
        }catch (Exception ex){
            datatemp.setIdProvinsiHer(null);
        }
        datatemp.setNamaProvinsiHer(requestData.getNamaProvinsiHer());
        try {
            datatemp.setIdNegaraHer(Long.valueOf(requestData.getIdNegaraHer()));
        }catch (Exception ex){
            datatemp.setIdNegaraHer(null);
        }
        datatemp.setNamaNegaraHer(requestData.getNamaNegaraHer());
        datatemp.setNoTlpRumahHer(requestData.getNoTlpRumahHer());
        datatemp.setNoHpHer(requestData.getNoHpHer());
        if (requestData.getIsNamaPenerimaSama().equals("1"))
            datatemp.setIsNamaPenerimaSama(true);
        else datatemp.setIsNamaPenerimaSama(false);
        if (requestData.getIsTglLahirSama().equals("1"))
            datatemp.setIsTglLahirSama(true);
        else datatemp.setIsTglLahirSama(false);
        if (requestData.getIsSuskelSama().equals("1"))
            datatemp.setIsSuskelSama(true);
        else datatemp.setIsSuskelSama(false);
        try {
            if (requestData.getIsKantorBayarSama().equals("1"))
                datatemp.setIsKantorBayarSama(true);
            else datatemp.setIsKantorBayarSama(false);
        }catch (Exception ex){
            datatemp.setIsKantorBayarSama(false);
        }
        try{
            if (requestData.getIsAlamatSama().equals("1"))
                datatemp.setIsAlamatSama(true);
            else datatemp.setIsAlamatSama(false);
        }catch (Exception ex){
            datatemp.setIsAlamatSama(false);
        }

        if (requestData.getIsAdaPhoto().equals("1"))
            datatemp.setIsAdaPhoto(true);
        else datatemp.setIsAdaPhoto(false);
        if (requestData.getIsAdaKtp().equals("1"))
            datatemp.setIsAdaKtp(true);
        else datatemp.setIsAdaKtp(false);
        if (requestData.getIsAdaKk().equals("1"))
            datatemp.setIsAdaKk(true);
        else datatemp.setIsAdaKk(false);
        if (requestData.getIsDataLengkap().equals("1"))
            datatemp.setIsDataLengkap(true);
        else datatemp.setIsDataLengkap(false);
        if (requestData.getIsDataLengkap().equals("1"))
            datatemp.setIsDataLengkap(true);
        else datatemp.setIsDataLengkap(false);
        if (requestData.getIsPenerimaMenikahLagi().equals("1"))
            datatemp.setIsPenerimaMenikahLagi(true);
        else datatemp.setIsPenerimaMenikahLagi(false);
        datatemp.setTempatMenikah(requestData.getTempatMenikah());
        try {
            datatemp.setTglMenikah(formatter1.parse(requestData.getTglMenikah()));
        } catch (ParseException e) {
            datatemp.setTglMenikah(null);
        }
        datatemp.setNamaSerah(requestData.getNamaSerah());
        datatemp.setKeteranganSerah(requestData.getKeteranganSerah());
        datatemp.setUpdatedAt(new Date());
        if (requestData.getIsSudahHer().equals("1"))
            datatemp.setIsSudahHer(true);
        else datatemp.setIsSudahHer(false);
        MessageUtil messageUtil;
        try {
            herRegistrasiDetailRepository.save(datatemp);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_TRANSACTION+Prefix.PATH_REGISTRASI_ULANG+"/report-her")
    public void report(HttpServletResponse httpServletResponse, @RequestParam("nip") String nip, @RequestParam("tipeReport") String tipeReport, @RequestParam(name="tahunHer", required = false) String tahunHer, @RequestParam(name="download", required = false) Boolean download){
        String jasperFileName = null;
        Map<String, Object> parameters = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

        try {

            switch (tipeReport) {
                case "Resi_Her_Registrasi" :

                    jasperFileName = "BuktiPenerimaanFormulirRegistrasi";
                    parameters = new HashMap<>();
                    parameters.put("nip",nip);
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

    @GetMapping(Prefix.PATH_API+Prefix.PATH_PROVINSI+Prefix.SELECT2)
    public List<Select2Default> select2Api(Pageable pageable, @RequestParam(name = "searchParam",required = false)String q){
        Iterable<Provinsi> it = provinsiRepository.findAllByNamaProvinsiLike( "%"+q+"%");
        if (q==null){
            it =provinsiRepository.findAll();
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            ProvinsiDto provinsiDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getNamaProvinsi(),provinsiDto));
        });
        return select2DefaultList;
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_KOTA+Prefix.SELECT2+"/{idProvinsi}")
    public List<Select2Default> select2Kota(Pageable pageable, @PathVariable("idProvinsi") Long id,@RequestParam(name = "searchParam",required = false)String q){
        Iterable<Kota> it = kotaRepository.findAllByProvinsi_Id(id);
        if (q != null){
            it = kotaRepository.findAllByProvinsi_IdAndNamaKotaLike(id, q);
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            KotaDto kotaDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getNamaKota(),kotaDto));
        });
        return select2DefaultList;
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_KECAMATAN+Prefix.SELECT2+"/{idKota}")
    public List<Select2Default> select2Kecamatan(Pageable pageable, @PathVariable("idKota") Long id,@RequestParam(name = "searchParam",required = false)String q){
        Iterable<Kecamatan> it = kecamatanRepository.findAllByKota_Id(id);
        if (q!=null){
            it = kecamatanRepository.findAllByKota_IdAndNamaKecamatanLike(id, q);
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            KecamatanDto kecamatanDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getNamaKecamatan(),kecamatanDto));
        });
        return select2DefaultList;
    }

    @GetMapping(Prefix.PATH_API+Prefix.PATH_KELURAHAN+Prefix.SELECT2+"/{idKecamatan}")
    public List<Select2Default> select2Kelurahan(Pageable pageable, @PathVariable("idKecamatan") Long id,@RequestParam(name = "searchParam",required = false)String q){
        Iterable<Kelurahan> it = kelurahanRepository.findAllByKecamatan_Id(id);
        if (q!=null){
            it = kelurahanRepository.findAllByKecamatan_IdAndNamaKelurahanLike(id, q);
        }
        List<Select2Default>select2DefaultList = new ArrayList<>();
        it.forEach((k)->{
            KelurahanDto kelurahanDto = k.toDto();
            select2DefaultList.add(new Select2Default(String.valueOf(k.getId()),k.getNamaKelurahan(),kelurahanDto));
        });
        return select2DefaultList;
    }

}
