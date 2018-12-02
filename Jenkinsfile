pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /home/jenkinsbuild/.m2:/home/jenkinsbuild/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
                steps {
                    sh 'mvn test'
                }
                /*post {
                    always {
                        junit 'target/surefire-reports/*.xml'
                    }
                }*/
        }
    }
}
