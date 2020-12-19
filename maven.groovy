/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
    
    stage('Compile') {
        env.STAGE = 'Compile'
        bat './mvnw.cmd clean compile -e'
    }

    stage('Test'){
        env.STAGE = 'Test'
        bat './mvnw.cmd clean test -e'
    }

    stage('Jar'){
        env.STAGE = 'Jar'
        bat './mvnw.cmd clean package -e'
    }

    stage('Sonar') {
        env.STAGE = 'Sonar'
        // Nombre extraido desde Jenkins > Global tool configuration > SonarQube Scanner
        def scannerHome = tool 'sonar-scanner';

        // Nombre extraido desde Jenkins > Configurar el sistema > SonarQube servers
        withSonarQubeEnv('sonar-server') {
            bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }

    stage('Nexus') {
        env.STAGE = 'Nexus'
        nexusPublisher nexusInstanceId: 'NexusLocal',
            nexusRepositoryId: 'test-nexus',
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

return this;