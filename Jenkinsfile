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
        stage('Generate Allure Report') {
			steps {
				sh 'mvn io.qameta.allure:allure-maven:report'
            }
        }
        stage('Reports') {
			steps {
				publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/allure-maven-plugin',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report'
                ])
            }
        }
    }
}