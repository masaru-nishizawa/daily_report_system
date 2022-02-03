package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Application Lifecycle Listener implementation class PropertiesListener
 *
 */
@WebListener
public class PropertiesListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public PropertiesListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
         // TODO Auto-generated method stub
    }

    /**
     * Webアプリケーションの起動時に実行する処理
     */
    public void contextInitialized(ServletContextEvent arg0)  {
         ServletContext context = arg0.getServletContext();

       //プロパティファイルを読み込み、アプリケーションスコープに設定する
         try {
             InputStream is = PropertiesListener.class.getClassLoader().getResourceAsStream("application.properties");

             Properties properties = new Properties();
             properties.load(is);//pepper.propertiesの読み込み
             is.close();

             Iterator<String> pit = properties.stringPropertyNames().iterator(); //pepper.properties内のkeyを「stringPropertyNames()」で列挙し、iterator()でIteratorにキャストしている, key="pepper"
             while (pit.hasNext()) {
                 String pname=pit.next();
                 context.setAttribute(pname, properties.getProperty(pname)); //getProperty(key)でpepper.properties内のkeyのvalueを取得する, 今回の場合はkey="pepper",value = "6Ab3mtmG"
             }
         }catch (NullPointerException e) {
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

 }