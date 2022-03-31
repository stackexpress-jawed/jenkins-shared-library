def send(URL_WEBHOOK, COLOR) {
   
   echo "${URL_WEBHOOK}"
   
   def conn = new URL("${URL_WEBHOOK}").openConnection()
   conn.requestMethod = 'POST'
  
   def msg = """{
                   "@type": "MessageCard",
                   "@context": "http://schema.org/extensions",
                   "themeColor": "bd8feb",
                   "summary": "Notification from ${env.JOB_NAME}",
                   "sections": [{
                       "activityTitle": "Notification from ${env.JOB_NAME}",
                       "activitySubtitle": "<span style=\'\\\'\'color: #bd8feb;\'\\\'\'>Latest status of build #${env.BUILD_NUMBER}</span>",
                       "activityImage": "https://www.jenkins.io/images/logos/jenkins/jenkins.png",
                       "facts": [
                           {
                               "name": "Pipeline",
                               "value": "[${env.JOB_NAME}](${env.BUILD_URL}/console)"
                           },
                           {
                               "name": "Git Branch",
                               "value": "`${env.GIT_BRANCH}`"
                           },
                           {
                               "name": "Build Number",
                               "value": "${env.BUILD_DISPLAY_NAME}"
                           },
                           {
                               "name": "Build Status",
                               "value": "STARTED"
                           }],
                       "markdown": true
                       }],
                       "potentialAction": [{
                           "@type": "OpenUri",
                           "name": "View Build",
                           "targets": [{
                           "os": "default",
                           "uri": "${env.BUILD_URL}"
                       }]
                   }]
               }""".stripMargin().stripIndent()
   
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
