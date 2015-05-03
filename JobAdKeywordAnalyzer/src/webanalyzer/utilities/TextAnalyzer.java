package webanalyzer.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;



public class TextAnalyzer {
	public static String standardizeKeyword(String word){
		
		if(word.toLowerCase().matches("((javascript|js|))"))
			return "JavaScript";
		
		if(word.toLowerCase().matches("((java))"))
			return "Java";
		
		if(word.toLowerCase().matches("((htm|html))"))
			return "HTML";
		
		if(word.toLowerCase().matches("((html5|html5.))"))
			return "HTML5";
		
		if(word.toLowerCase().matches("((css|css-layout|css2.1))"))
			return "CSS";
		
		if(word.toLowerCase().matches("((css3))"))
			return "CSS3";
		
		if(word.toLowerCase().matches("((asp))"))
			return "ASP";
		
		if(word.toLowerCase().matches("((php|php.))"))
			return "PHP";
		
		if(word.toLowerCase().matches("((asp.net|asp dot net|asp net))"))
			return "ASP.NET";
		
		if(word.toLowerCase().matches("((sql|sql.))"))
			return "SQL";
		
		if(word.toLowerCase().matches("((webtrend))"))
			return "WebTrends";
		
		if(word.toLowerCase().matches("((drupal6|drupal7|drupal-views))"))
			return "Drupal";
		
		if(word.toLowerCase().matches("((google analytics))"))
			return "Google Analytics";
		
		if(word.toLowerCase().matches("((cms))"))
			return "CMS";
		
		if(word.toLowerCase().matches("((xml|android-xml|xml-parsing))"))
			return "XML";
		
		if(word.toLowerCase().matches("((json))"))
			return "JSON";
		
		if(word.toLowerCase().matches("((xslt))"))
			return "XSLT";
		
		if(word.toLowerCase().matches("((jquery))"))
			return "JQuery";
		
		if(word.toLowerCase().matches("((rails|ror|ruby-on-rails))"))
			return "Rails";
		
		if(word.toLowerCase().matches("((rest|restful))"))
			return "RESTful";
		
		if(word.toLowerCase().matches("((html-unit|htmlunit))"))
			return "HtmlUnit";
		
		if(word.toLowerCase().matches("((mocha))"))
			return "mocha";
		
		if(word.toLowerCase().matches("((mongodb|mongo-db))"))
			return "MongoDB";
		
		if(word.toLowerCase().matches("((grunt))"))
			return "Grunt";
		
		if(word.toLowerCase().matches("((capistrano))"))
			return "Capistrano";
		
		if(word.toLowerCase().matches("((dancer))"))
			return "Dancer";
		
		if(word.toLowerCase().matches("((catalyst))"))
			return "Catalyst";
		
		if(word.toLowerCase().matches("((django))"))
			return "Django";
		
		if(word.toLowerCase().matches("((sinatra|sinatra-assetpack|sinatra-activerecord))"))
			return "Sinatra";
		
		if(word.toLowerCase().matches("((ajax))"))
			return "AJAX";
		
		if(word.toLowerCase().matches("((backbone.js|backbone|back-bone))"))
			return "Backbone.js";
		
		if(word.toLowerCase().matches("((ember.js|ember))"))
			return "Ember.js";
		
		if(word.toLowerCase().matches("((knockout.js|knockout))"))
			return "Knockout.js";
		
		if(word.toLowerCase().matches("((angular.js|angular))"))
			return "Angular.js";
		
		if(word.toLowerCase().matches("((node.js|node))"))
			return "Node.js";
	
		if(word.toLowerCase().matches("((rhino.js|rhino))"))
			return "Rhino.js";
		
		if(word.toLowerCase().matches("((c))"))
			return "C";
		
		if(word.toLowerCase().matches("((.net))"))
			return ".NET";
		
		if(word.toLowerCase().matches("((c#))"))
			return "C#";
		
		if(word.toLowerCase().matches("((ooa))"))
			return "Object-oriented analysis";
		
		if(word.toLowerCase().matches("((ood))"))
			return "Object-oriented design";
		
		if(word.toLowerCase().contains("c++"))
			return "C++";
		
		if(word.toLowerCase().matches("((autocad))"))
			return "AutoCAD";
		
		if(word.toLowerCase().matches("((prototype))"))
			return "Prototype";
		
		if(word.toLowerCase().matches("((objective-c|objectivec|obj-c|objc|objective|objective-c.))"))
			return "Objective-C";
		
		if(word.toLowerCase().matches("((xcode|x-code|xcode.))"))
			return "Xcode";
		
		if(word.toLowerCase().matches("((vb.net|visual-basic))"))
			return "VB.NET";
		
		if(word.toLowerCase().matches("((git|git.))"))
			return "Git";
		
		if(word.toLowerCase().matches("((ado.net))"))
			return "ADO.NET";
		
		if(word.toLowerCase().matches("((silverlight))"))
			return "Silverlight";
		
		if(word.toLowerCase().matches("((WPF))"))
			return "Windows Presentation Foundation";
		
		if(word.toLowerCase().matches("((mvc))"))
			return "MVC";
		
		if(word.toLowerCase().matches("((svn))"))
			return "SVN";
		
		if(word.toLowerCase().matches("((ios))"))
			return "iOS";
		
		if(word.toLowerCase().matches("((intellij))"))
			return "IntelliJ";
		
		if(word.toLowerCase().matches("((agile))"))
			return "Agile";
		
		if(word.toLowerCase().matches("((nosql|no-sql))"))
			return "NoSQL";
		
		if(word.toLowerCase().matches("((cms))"))
			return "CMS";
		
		if(word.toLowerCase().matches("((cassandra))"))
			return "Cassandra";
		
		if(word.toLowerCase().matches("((symfony))"))
			return "Symfony";
		
		if(word.toLowerCase().matches("((phd|ph.d))"))
			return "Ph.D";
		
		if(word.toLowerCase().matches("((android))"))
			return "Android";
		
		if(word.toLowerCase().matches("((dojo))"))
			return "Dojo";
		
		if(word.toLowerCase().matches("((cordova))"))
			return "Cordova";
		
		if(word.toLowerCase().matches("((soap))"))
			return "SOAP";
		
		if(word.toLowerCase().matches("((cocoa))"))
			return "Cocoa";
		
		if(word.toLowerCase().matches("((eclipse))"))
			return "Eclipse";
		
		if(word.toLowerCase().matches("((wordpress))"))
			return "Wordpress";
		
		if(word.toLowerCase().matches("((perl))"))
			return "Perl";
		
		if(word.toLowerCase().matches("((python|python.))"))
			return "Python";
		
		if(word.toLowerCase().matches("((swing))"))
			return "Swing";
		
		if(word.toLowerCase().matches("((shell))"))
			return "Shell";
		
		if(word.toLowerCase().matches("((pearl))"))
			return "Pearl";
		
		if(word.toLowerCase().matches("((swing))"))
			return "Swing";
		
		if(word.toLowerCase().matches("((bash))"))
			return "Bash";
		
		if(word.toLowerCase().matches("((hibernate))"))
			return "Hibernate";
		
		if(word.toLowerCase().matches("((jsoup))"))
			return "Jsoup";
		
		if(word.toLowerCase().matches("((tika))"))
			return "Tika";
		
		if(word.toLowerCase().matches("((mysql))"))
			return "MySQL";
		
		if(word.toLowerCase().matches("((flex|flex.))"))
			return "FLEX";
		
		if(word.toLowerCase().matches("((directx))"))
			return "DirectX";
		
		if(word.toLowerCase().matches("((opencl))"))
			return "OpenCL";
		
		if(word.toLowerCase().matches("((vb|vb.))"))
			return "Visual Basic";
		
		if(word.toLowerCase().matches("((perforce))"))
			return "Perforce";
		
		if(word.toLowerCase().matches("((automation))"))
			return "Automation";
		
		if(word.toLowerCase().matches("((oo))"))
			return "Object-Oriented Programming";
		
		if(word.toLowerCase().matches("((uikit))"))
			return "UIKit";
		
		if(word.toLowerCase().matches("((j2ee))"))
			return "J2EE";
		
		if(word.toLowerCase().matches("((j2me))"))
			return "J2ME";
		
		if(word.toLowerCase().matches("((photoshop|ps))"))
			return "Photoshop";
		
		if(word.toLowerCase().matches("((kentico))"))
			return "Kentico";
		
		if(word.toLowerCase().matches("((webapi))"))
			return "WebAPI";
		
		if(word.toLowerCase().matches("((excel))"))
			return "Excel";
		
		if(word.toLowerCase().matches("((unix|unix.))"))
			return "Unix";
		
		if(word.toLowerCase().matches("((sybase|sybase.))"))
			return "Sybase";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((powerbuilder|powerbuilder.))"))
			return "Powerbuilder";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		if(word.toLowerCase().matches("((mainframe|mainframe.))"))
			return "Mainframe";
		
		return null;
	}
	
	
	public static void main(String agrs[]){
		System.out.println(TextAnalyzer.standardizeKeyword("ojbc"));
		
		
	}

}
