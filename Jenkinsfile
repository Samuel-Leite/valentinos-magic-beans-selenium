pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Samuel-Leite/valentinos-magic-beans-selenium'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Denvironment=qa -Dbrowser=chrome -Dheadless=true -Dlighthouse=true'
            }
        }
        stage('Lighthouse Audit') {
            steps {
                sh '''
                    mkdir -p target/lighthouse-reports
                    lighthouse https://valentinos-magic-beans.click/ \
                      --output html --output json \
                      --output-path=target/lighthouse-reports/lighthouse-login.html \
                      --quiet --chrome-flags="--headless"
                '''
            }
        }
        stage('Percy Visual Tests') {
            steps {
                withCredentials([string(credentialsId: 'PERCY_TOKEN', variable: 'PERCY_TOKEN')]) {
                    sh 'percy exec -- mvn test -Denvironment=qa -Dbrowser=chrome -Dheadless=true'
                }
            }
        }
        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/screenshots/**', fingerprint: true
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target',
                    reportFiles: 'report.html',
                    reportName: 'Harvest of Quality Report',
                    useWrapperFileDirectly: true
                ])

                // Gerar Allure Report via CLI usando instalação configurada
                script {
                    def allureHome = tool 'ALLURE_HOME'  // nome que você configurou no Jenkins
                    sh "${allureHome}/bin/allure generate target/allure-results --clean -o target/allure-report"
                }

                // Publicar Allure Report como HTML
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/allure-report',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report'
                ])
            }
        }
    }
}