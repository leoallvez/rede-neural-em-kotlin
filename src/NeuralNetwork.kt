// Written by Sean Franklin in 2018
import java.util.*
// Este simples de neurônio único pode ser ensinado a aprender padrões ** muito ** simples
// que recebe 2 entradas inteiros e retorna uma única resposta inteira.
class NeuralNetwork {

    private val random = Random()
    private var weight1: Float = random.nextFloat() % 1
    private var weight2: Float = random.nextFloat() % 1

    fun think(input1: Int, input2: Int): Int {
        val output: Float = weight1*input1 + weight2 * input2
        return output.toInt()
    }

    fun train(input1: Int, input2: Int, output: Int){
        val out = think(input1, input2)
        val error = output - out
        weight1 += (0.01f*error*input1)
        weight2 += (0.01f*error*input2)
    }
}

fun main(args: Array<String>){
    // vai fazer dois cérebros diferentes
    val nn_add_pairs = NeuralNetwork() // Este pode somar dois números de um dígito
    val nn_add_and_double = NeuralNetwork() // este pode somar dois números e multiplicar o resultado por 2

    // este conjunto de treinamento ensina o cérebro a somar dois números de um dígito
    (0..1000).forEach {
        for(i in 1..10){
            val b = i+1
            nn_add_pairs.train(i, b, i+b)
        }
    }

    println("Brain that adds--\n")
    println("6 + 6 = " + nn_add_pairs.think(6, 6)) // deve exibir 12 se o treinamento foi suficiente
    println("3 + 4 = " + nn_add_pairs.think(3, 4)) // deve exibir 7
    println("9 + 9 = " + nn_add_pairs.think(9, 9))
    println()
    // este conjunto de treinamento ensina o cérebro a reconhecer o padrão (x + y) * 2
    // por exemplo:
    // 4 e 6 = (4 + 6) * 2 = 20
    (0..1000).forEach {
        for ( i in 1..10 ){
            val b = i+1
            nn_add_and_double.train(i, b, (i+b)*2)
        }
    }

    println("Brain that adds then doubles--\n")
    println("(4 + 7)*2 = " + nn_add_and_double.think(4, 7)) // deve exibir 22
    println("(6 + 8)*2 = " + nn_add_and_double.think(6, 8)) // deve exibir 28
    println("(1 + 2)*2 = " + nn_add_and_double.think(1, 2)) // deve exibir 6
}