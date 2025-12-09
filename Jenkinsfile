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
                sh 'mvn test -Denvironment=qa -Dbrowser=chrome -Dheadless=true -Dlighthouse=false'
            }
        }
        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'

                // Arquiva screenshots
                archiveArtifacts artifacts: 'target/screenshots/**', fingerprint: true

                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}