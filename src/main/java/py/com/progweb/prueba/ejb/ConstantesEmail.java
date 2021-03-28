/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.progweb.prueba.ejb;

public class ConstantesEmail {

    public static final String MAIL_USER = "electivabackend@gmail.com";
    public static final String MAIL_PASSWORD = "electivabe2020";
    
    public static final String TEMPLATE = ""
+ "<html>\n" +
"	<body>\n" +
"		<h3 style=\"text-align: center\">\n" +
"			Comprobante de Uso de Puntos.\n" +
"		</h3>\n" +
"		<div>\n" +
"			<h5>\n" +
"				<b> Cliente: </b> {{}}\n" +
"			</h5>\n" +
"			<h5>\n" +
"				Utilizó: <b>[[]] puntos</b>\n" +
"			</h5>\n" +
"		</div>\n" +
"	</body>\n" +
"</html>";
}
