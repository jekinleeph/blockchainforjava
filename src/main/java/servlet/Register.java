package servlet;

import core.BlockChain;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: cfx
 * @Description: 注册网络节点
 * @Date: Created in 2018/5/10 11:26
 */
@WebServlet("/nodes/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 读取客户端传递过来的数据并转换成JSON格式
        BufferedReader reader = req.getReader();
        String input = null;
        StringBuffer requestBody = new StringBuffer();
        while ((input = reader.readLine()) != null) {
            requestBody.append(input);
        }
        JSONObject jsonValue = new JSONObject(requestBody.toString());
        BlockChain blockChain = BlockChain.getInstance();
        blockChain.registerNode(jsonValue.getString("nodes"));

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(new JSONObject().append("message","The Nodes is : " + blockChain.getNodes()));
        printWriter.close();

    }
}
