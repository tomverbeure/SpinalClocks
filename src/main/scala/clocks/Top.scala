
package clocks

import spinal.core._
import spinal.lib._

class Top() extends Component {

    val io = new Bundle {
        val led_red         = out(Bool)
        val led_green       = out(Bool)
        val led_blue        = out(Bool)
        val switch          = in(Bool)
    }

    val srcClkDomain = ClockDomain.external("src", frequency = FixedFrequency(25 MHz))
    srcClkDomain.reset.setName("src_reset_")

    val destClkDomain = ClockDomain.external("dest", frequency = FixedFrequency(100 MHz))
    destClkDomain.reset.setName("dest_reset_")

    val src_domain = new ClockingArea(srcClkDomain) {
        val src_switch = RegNext(io.switch) init(False)

        io.led_red  := RegNext(src_switch)
    }

    val dest_domain = new ClockingArea(destClkDomain) {

        val led_green = Reg(Bool)
        val led_blue  = Reg(Bool)

        led_green := src_domain.src_switch
        led_blue  := src_domain.src_switch
    }

    io.led_green := dest_domain.led_green
    io.led_blue  := dest_domain.led_blue
    
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
