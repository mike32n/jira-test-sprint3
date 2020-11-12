pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Build phase: '
                sh 'mvn clean'
            }
        }
        stage('Test') {
            parallel {
                stage('Test Login') {
                    steps {
                        echo 'Testing in progress: '
                        sh "mvn test"
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleanup phase: '
            cleanWs()
        }
    }
}