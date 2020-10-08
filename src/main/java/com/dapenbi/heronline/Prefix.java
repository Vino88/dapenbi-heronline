package com.dapenbi.heronline;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Prefix {
    /* APP PREFIX */
    public static final String appName = "Dapenbi - Registrasi Ulang Online";

    /* PATH PREFIX*/
    public static final String PATH_DASHBOARD = "/";
    public static final String PATH_API = "/api";
    public static final String PATH_MASTER_DATA = "/master-data";
    public static final String PATH_LOGOUT = "/logout";
    public static final String PATH_LOGIN = "/login";
    public static final String PATH_PPBI = "/ppbi";
    public static final String PATH_MPENSIUN = "/master-pensiun";
    public static final String PATH_ROLE = "/role";
    public static final String PATH_USER = "/users";
    public static final String PATH_TRANSACTION= "/transaction";
    public static final String PATH_REGISTRASI_ULANG= "/registrasi-ulang";
    public static final String PATH_MENU= "/menu";
    public static final String PATH_PEREKAMAN_DATA= "/perekaman-data";
    public static final String SELECT2= "/select2";
    public static final String PATH_GENERATE_PASSWORD = "/generate-pass";
    public static final String PATH_REPORT = "/report";
    public static final String PATH_SUDAH_HER = "/sudah-registrasi";
    public static final String PATH_BELUM_HER = "/belum-registrasi";
    public static final String PATH_PROVINSI = "/provinsi";
    public static final String PATH_KOTA = "/kota";
    public static final String PATH_KECAMATAN = "/kecamatan";
    public static final String PATH_KELURAHAN = "/kelurahan";

    public static final String LocalComputerName(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return "";
        }
        return ip.getCanonicalHostName();
    }

    public static final String getURLBase(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        String contextPath = request.getContextPath();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port + contextPath;
    }
}
