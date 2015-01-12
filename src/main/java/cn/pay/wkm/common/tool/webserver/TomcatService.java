/**
 * Project Name:tool
 * File Name:TomcatService.java
 * Package Name:cn.pay.wkm.common.tool.webserver
 * Date:2015年1月12日下午1:33:42
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.webserver;


/**
 * ClassName:TomcatService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月12日 下午1:33:42 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TomcatService {

	/** tomcat服务器
		private Tomcat tomcat = null;
		

		public String script = null;

		@Before	
		public void initService() throws Exception {
			
			System.setProperty("catalina.home", "tomcat");
			tomcat = new Tomcat();
			tomcat.setSilent(true);
			tomcat.getEngine().setName("woshua");
			
			Connector connector = new Connector(Http11NioProtocol.class.getName());
			connector.setPort(8089);
			connector.setAttribute("backlog", "500");
			connector.setAttribute("minSpareThreads", "4");
			connector.setAttribute("maxThreads", "100");
			connector.setAttribute("pollerThreadCount", "4");
			connector.setAttribute("maxKeepAliveRequests", "100");
			connector.setAttribute("socketBuffer", "10240");
			connector.setAttribute("maxSavePostSize", 1024 * 1024);
			connector.setAttribute("connectionTimeout", 1000 * 60 * 2);
			connector.setAttribute("socket.reuseAddress", true);
			connector.setURIEncoding("UTF-8");
			connector.setXpoweredBy(false);
			
			tomcat.setConnector(connector);
			tomcat.getService().addConnector(connector);
			Context ctx = tomcat.addContext("/", new File("E:/workspace_idea/woshua/woshua_v3/static").getAbsolutePath());
			ctx.setReloadable(false);
			ctx.setCookies(false);
			ctx.setSessionTimeout(3000);
			ctx.setSessionCookieName("WSHSNO");
			
			Tomcat.addServlet(ctx, "test", new HttpServlet() {
				
				private static final long serialVersionUID = 1L;

				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					System.out.println("=====");
					System.out.println(req.getParameter("wkm"));
				}
			});
			
			ctx.addServletMapping("/*", "test");**/
			/**
				def ctx = tomcat.addContext('/',  new File('static').absolutePath)
				ctx.reloadable        = false
				ctx.cookies           = false
				ctx.sessionTimeout    = 30
				ctx.sessionCookieName = 'WSHSNO'
				tomcat.addServlet(ctx, 'handler', new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						req.setCharacterEncoding('UTF-8')
						resp.setCharacterEncoding('UTF-8')
		                resp.setHeader 'Expires', '-1'
		                resp.setHeader 'Pragma', 'no-cache'
		                resp.setHeader 'Cache-Control', 'no-cache, no-store, must-revalidate'
						resp.setHeader 'Server', 'cnepay ws trans front/1.0.2 (me@yinheli.com)'
						if (req.requestURI == '/favicon.ico') {
							resp.contentType = 'image/x-icon'
							resp.outputStream << new File('static/favicon.ico').newInputStream()
							resp.outputStream.flush()
							return
						}
		                if(req.requestURI.indexOf('/wahaha') == 0){
		                   resp.setHeader('Access-Control-Allow-Origin', '*')
		                }

						def render = { content ->
							if (content instanceof Map || content instanceof Collection) {
		                        Cookie cookie = content.get('cookie')
		                        if(cookie){
		                            content.remove('cookie')
		                            println('***    send cookie     ***')
		                            resp.addCookie(cookie)
		                        }
								resp.setContentType('application/json;charset=UTF-8')
		                        log.info('resp='+JSONArray.toJSONString(content, false))
								resp.writer.write JSONArray.toJSONString(content, false)
							} else if (content instanceof String) {
								resp.setContentType('text/plain;charset=UTF-8')
								resp.writer.write content
							} else if (content instanceof File) {
		                        resp.setContentLength(content.length() as int)
		                        if(content.getName().toLowerCase().lastIndexOf(".apk") == (content.getName().length() -4)){
		                            resp.setContentType('application/vnd.android.package-archive')
		                        }
		                        resp.outputStream << content.newInputStream()
		                    }
						}

						try {
							def binding = new Binding()
							binding.setVariable("req", req)
							binding.setVariable("resp", resp)
							binding.setVariable("log", log)
							binding.setVariable("render", render)
							binding.setVariable("q2", getServer())
							binding.setVariable("session", null)
							binding.setVariable("binding", binding)
							if (script.endsWith("groovy")) {
								GroovySupport.gse.run(script, binding)
							} else {
								Class<Script> c = (Class<Script>) Class.forName(script)
								Script sc = c.getConstructor(Binding.class).newInstance(binding)
								sc.run()
							}
						} catch (e) {
							resp.status = 500
//		                    resp.outputStream << new File('static/50x.html').newInputStream()
							log.error e
						}
					}
				})
				ctx.addServletMapping('/*', 'handler')

				if (Boolean.parseBoolean(System.getProperty('dev', 'false'))) {
					tomcat.addServlet(ctx, 'druid', 'com.alibaba.druid.support.http.StatViewServlet')
					ctx.addServletMapping('/druid/*', 'druid')
				}
				**/
	/**
			}   **/

			/**
			@Test
			public void startService() throws Exception {
				tomcat.start();
				Thread.sleep(1000*60);
			}

			
			public void stopService() throws Exception {
				tomcat.stop();
			}

			
			public void destroyService() throws Exception {
				tomcat.destroy();
			}
			**/
}

