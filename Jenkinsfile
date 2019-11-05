pipeline {
   agent any

   stages {
       stage ('Compile Stage') {

           steps {
               withMaven(maven : 'Maven-3.6.2') {
                   sh 'mvn clean compile'
               }
           }
       }

       stage ('Testing Stage') {

           steps {
               withMaven(maven : 'Maven-3.6.2') {
                   sh 'mvn test'
               }
           }
           post {
               always {
                   junit 'target/surefire-reports/*.xml' 
               }
           }
       }
       
   }
}