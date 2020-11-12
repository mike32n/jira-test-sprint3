pipeline {
    agent any
     parameters {
            string(name: 'browserToRun', defaultValue: 'both', description: 'Browsers to run: both, chrome, firefox')
            string(name: 'chrome', defaultValue: 'chrome', description: 'chrome browser')
            string(name: 'firefox', defaultValue: 'firefox', description: 'firefox browser')
            }
    stages {
        stage('Build') {
            steps {
                echo 'Build phase: '
                sh 'mvn clean'
            }
        }
        stage('Test') {
            parallel {
                stage('run with chrome') {
                    when {
                        expression { params.browserToRun == 'both' || params.browserToRun == 'chrome' }
                    }
                    steps {
                        echo 'Test phase with chrome: '
                        sh "mvn -Dtest=TestPlan#successfulLogin -Dbrowser=${chrome} test"
                    }
                }
                stage('run with firefox') {
                    when {
                        expression { params.browserToRun == 'both' || params.browserToRun == 'firefox' }
                    }
                    steps {
                        echo 'Test phase with firefox: '
                        sh "mvn -Dtest=TestPlan#successfulLogin -Dbrowser=${firefox} test"
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