def send(URL_WEBHOOK) {
   
   echo "${URL_WEBHOOK}"
   
   def conn = new URL("${URL_WEBHOOK}").openConnection()
   conn.requestMethod = 'POST'
   
   def msg = '{ "text" : "hello groovy" }'
   conn.setDoOutput(true)
   conn.setRequestProperty("Content-Type", "application/json")
   conn.getOutputStream().write(msg.getBytes("UTF-8"))

   // Send request
   resp = conn.getResponseCode()
   println("Response Code : ${resp}")

   if (resp.equals(200)) {
      println("Message has been sent successfully")
   } else {
      println("Error: Message not sent");
      println(conn.getInputStream().getText())
   }
   
}
