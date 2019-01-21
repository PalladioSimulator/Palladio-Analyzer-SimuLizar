pipeline {
    agent none

    options {
        timeout(time: 30, unit: 'MINUTES')
    }
    stages {
        stage('TEST') {
            steps {
                sh 'printenv'
            }
        }
        stage('Build_Master') {
            agent {
                docker {
                    image 'custom_maven:latest'
                    args '-v /media/data/m2-cache/:/home/jenkinsbuild/tmp_cache -m 4G --storage-opt size=20M'
                }
            }
            when {
                expression {
                    GIT_BRANCH = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
                    return GIT_BRANCH == 'master'
                }
            }
            stages {
                stage('load_cache') {
                    steps {
                        sh 'cp -r /home/jenkinsbuild/tmp_cache/* /home/jenkinsbuild/.m2/'
                    }
                }
                stage('build') {
                    steps {
                        sh 'mvn -B -DskipTests clean package'
                    }
                }
                stage('save_cache') {
                    steps {
                        sh 'cp -r /home/jenkinsbuild/.m2/* /home/jenkinsbuild/tmp_cache/'
                    }
                }
            }
        }
        stage('Build_Slave') {
            agent {
                docker {
                    image 'custom_maven:latest'
                    args '-v /media/data/m2-cache/:/home/jenkinsbuild/tmp_cache:ro -m 4G --storage-opt size=20M'
                }
            }
            when {
                expression {
                    GIT_BRANCH = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
                    return !(GIT_BRANCH == 'master')
                }
            }
            stages {
                stage('load_cache') {
                    steps {
                        sh 'cp -r /home/jenkinsbuild/tmp_cache/* /home/jenkinsbuild/.m2/'
                    }
                }
                stage('build') {
                    steps {
                        sh 'mvn -B -DskipTests clean package'
                    }
                }
            }
        }
        /*stage('Test') {
                steps {
                    //sh 'echo "Tests are disabled"'
                    sh 'mvn test'
                }
                post {
                    always {
                        junit 'target/surefire-reports/*.xml'
                    }
                }
        }*/
    }
}
