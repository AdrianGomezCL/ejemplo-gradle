pipeline {
    agent any

    parameters {
        choice(
            name:'compileTool',
            choices: ['Gradle', 'Maven'],
            description: 'Seleccione herramienta de compilacion'
        )
    }

    stages {
        stage('pipeline') {
            steps {
                script {

                    switch(params.compileTool)
                    {
                        case 'Gradle':
                            load 'gradle.groovy'
                        break;
                        case 'Maven':
                            load 'maven.groovy'
                        break;
                    }
                }
            }
        }
    }
}