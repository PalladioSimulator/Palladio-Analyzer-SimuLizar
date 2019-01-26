pipeline {
    agent none

    options {
        timeout(time: 30, unit: 'MINUTES')
    }
    stages {
        stage('Build_Master') {
            agent {
                docker {
                    image 'custom_maven:latest'
                    args '-v /media/data/empty_maven_folder/:/root/.m2:ro -v /media/data/m2-cache/:/home/jenkinsbuild/tmp_cache -m 4G --storage-opt size=20G'
                }
            }
            when {
                expression {
                    if (env.CHANGE_TARGET) {    // check pull request
                        return false
                    }
                    return env.GIT_BRANCH == 'master'
                }
            }
            stages {
                stage('load_cache') {
                    steps {
                        sh 'printenv'
                        sh 'mkdir /home/jenkinsbuild/.m2/'
                        sh 'cp -r /home/jenkinsbuild/tmp_cache/. /home/jenkinsbuild/.m2/'
                    }
                }
                stage('build') {
                    steps {
                        sh 'mvn -B -DskipTests clean package'
                    }
                }
                stage('save_cache') {
                    steps {
                        sh 'cp -r /home/jenkinsbuild/.m2/. /home/jenkinsbuild/tmp_cache/'
                    }
                }
            }
        }
        stage('Build_Slave') {
            agent {
                docker {
                    image 'custom_maven:latest'
                    args '-v /media/data/empty_maven_folder/:/root/.m2:ro -v /media/data/m2-cache/:/home/jenkinsbuild/tmp_cache:ro -m 4G --storage-opt size=20G'
                }
            }
            when {
                expression {
                    if (env.CHANGE_TARGET) {    // check pull request
                        return true
                    }
                    return !(env.GIT_BRANCH == 'master')
                }
            }
            stages {
                stage('load_cache') {
                    steps {
                        sh 'printenv'
                        sh 'mkdir /home/jenkinsbuild/.m2/'
                        sh 'cp -r /home/jenkinsbuild/tmp_cache/. /home/jenkinsbuild/.m2/'
                    }
                }
                stage('build') {
                    steps {
                        sh 'mvn -B -DskipTests clean package'
                    }
                }
            }
        }
    }
}
