package example.hellosecurity.security.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class HttpServletResponseUtil {

    public static void response(HttpServletResponse response, String message){
        PrintWriter printWriter = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            printWriter = response.getWriter();
            printWriter.println(message);
        } catch (Exception e) {
            log.error("AdminAuthenticationSuccessHandler处理异常!", e);
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

}
