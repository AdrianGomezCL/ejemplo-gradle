pipeline {
    agent any

    parameters {
        choice(
            name:'compileTool',
            choices: ['Maven', 'Gradle'],
            description: 'Seleccione herramienta de compilacion'
        )
    }

    stages {
        stage('pipeline') {
            steps {
                script {

                    params.compileTool

                    switch(params.compileTool)
                    {
                        case 'Maven':
                            load 'maven.groovy'
                        break;
                        case 'Gradle':
                            load 'gradle.groovy'
                        break;
                    }
                }
            }
        }
    }
}