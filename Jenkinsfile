pipeline {
    agent {
        docker {
            image 'custom_maven:latest'
            args '-v m2-volume:/home/jenkinsbuild/.m2'
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
                    //sh 'echo "Tests are disabled"'
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
