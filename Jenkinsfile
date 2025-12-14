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
				sh 'mvn clean test -Denvironment=qa -Dbrowser=chrome -Dheadless=true -Dlighthouse=false'
            }
        }
    }

    post {
		always {
			allure([
                path: 'allure-results'
            ])
        }
    }
}