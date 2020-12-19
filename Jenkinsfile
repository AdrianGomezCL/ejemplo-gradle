pipeline {
    agent any

    env.NOMBRE = 'Adrian Gomez'
    env.JOB = 'ejemplo-gradle'

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
                            def ejecucion = load 'maven.groovy'
                            ejecucion.call()
                        break;
                        case 'Gradle':
                            def ejecucion = load 'gradle.groovy'
                            ejecucion.call()
                        break;
                    }
                }
            }

            post {
                success: {
                    slackSend message: '[${NOMBRE}] [${JOB}] [${params.compileTool}] ejecución exitosa'
                }
                failure: {
                    slackSend message: '[${NOMBRE}] [${JOB}] [${params.compileTool}] ejecucion fallida en stage ${STAGE}'
                }
            }
        }
    }
}