pipeline {
    agent {
        docker {
            image 'custom_maven:latest'
            args '-v /home/jenkinsbuild/.m2:/home/jenkinsbuild/.m2 --storage-opt size=200M'
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
                    sh 'echo "Tests are disabled"'
                    //sh 'mvn test'
                }
                /*post {
                    always {
                        junit 'target/surefire-reports/*.xml'
                    }
                }*/
        }
    }
}
