
package clocks

import spinal.core._
import spinal.lib._

class Top() extends Component {

    val io = new Bundle {
        val led_red         = out(Bool)
        val led_green       = out(Bool)
        val switch          = in(Bool)
    }

    io.led_red   := RegNext(io.switch) init(False)
    io.led_green := RegNext(io.switch)
}


object TopVerilog{
    def main(args: Array[String]) {

        val config = SpinalConfig(
            defaultConfigForClockDomains = ClockDomainConfig(
                clockEdge           = RISING,
                resetKind           = ASYNC,
                resetActiveLevel    = LOW
            )
        )

        config.generateVerilog({
            val toplevel = new Top()
            toplevel
        })
        println("DONE")
    }
}
