pipeline {
    agent any

    stages {
        stage('pipeline') {
            steps {
                script {
                    stage('Build & Test') {
                        sh "gradle clean build"
                    }
                    
                    stage('Sonar') {
                        // Nombre extraido desde Jenkins > Global tool configuration > SonarQube Scanner
                        def scannerHome = tool 'sonar-scanner';

                        // Nombre extraido desde Jenkins > Configurar el sistema > SonarQube servers
                        withSonarQubeEnv('sonar-server') {
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                        }
                    }
                    
                    stage('Run') {
                        //
                    }

                    stage('Rest') {
                        //
                    }

                    stage('Nexus') {
                        //
                    }
                }
            }
        }
    }
}