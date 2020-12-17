pipeline {
    agent any

    stages {
        stage('pipeline') {
            steps {
                script {
                    stage('Build & Test') {
                        bat "gradle clean build"
                    }
                    
                    stage('Sonar') {
                        // Nombre extraido desde Jenkins > Global tool configuration > SonarQube Scanner
                        def scannerHome = tool 'sonar-scanner';

                        // Nombre extraido desde Jenkins > Configurar el sistema > SonarQube servers
                        withSonarQubeEnv('sonar-server') {
                            bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                        }
                    }
                    
                    stage('Run') {
                        bat 'nohup start gradle bootRun &'
                        sleep 20
                    }

                    stage('Rest') {
                        bat "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
                    }

                    stage('Nexus') {
                        nexusPublisher
                            nexusInstanceId: 'NexusLocal',
                            nexusRepositoryId: 'test-repo',
                            packages: [
                                [
                                    $class: 'MavenPackage',
                                    mavenAssetList: [
                                        [
                                            classifier: '',
                                            extension: 'jar',
                                            filePath: 'C:\\proyects\\diplomado\\gradle\\ejemplo-gradle\\build\\DevOpsUsach2020-0.0.1.jar'
                                        ]
                                    ],
                                    mavenCoordinate: [
                                        artifactId: 'DevOpsUsach2020',
                                        groupId: 'com.devopsusach2020',
                                        packaging: 'jar',
                                        version: '0.0.1'
                                    ]
                                ]
                            ]
                    }
                }
            }
        }
    }
}