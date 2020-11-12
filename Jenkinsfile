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
                    }
                    steps {
                        echo 'Testing in progress: '
                        sh "mvn -Dtest=TestPlan#successfulLogin test"
                    }
                    post {
                        always {
                            junit allowEmptyResults: true, testResults: '**/test-results/*.xml'
                        }
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
