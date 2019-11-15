package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: liyuntao
 * @date: 2019-06-25 9:36
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {

        //启动服务器监听8888端口
        ServerSocket server=new ServerSocket(8888);
        System.out.println("服务器启动，监听 8888 端口");

        while(!Thread.interrupted()) {
            //不停接收客户端请求
            Socket client = server.accept();

            //获取输入流
            InputStream ins=client.getInputStream();
            OutputStream out=client.getOutputStream();

            //读取请求内容
            int len=0;
            byte[] b=new byte[1024];
/*            while((len=ins.read(b))!=-1){
                System.out.println(new String(b,0,len));
            }*/

            BufferedReader reader=new BufferedReader(new InputStreamReader(ins));
            String s = reader.readLine();
            System.out.println(s);

            //给用户响应
            PrintWriter pw=new PrintWriter(out);
            BufferedReader fr=new BufferedReader(new FileReader("D:\\github\\httpserver\\webroot\\index.html"));

            //响应行
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html;charset=utf-8");
            pw.println("Contnet-Length: "+pw.);
            pw.println();
            pw.flush();



            String c=null;
            while((c= fr.readLine())!=null){
                pw.print(c);
            }

            reader.close();
            pw.close();
        }
    }
}
