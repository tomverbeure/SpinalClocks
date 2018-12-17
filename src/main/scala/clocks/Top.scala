
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

    ClockDomain.current.clock.setName("vo_clk")
    ClockDomain.current.reset.setName("vo_reset_")

    io.led_red   := RegNext(io.switch) init(False)
    io.led_green := RegNext(io.switch)

    val u_sub1 = new Sub1
    u_sub1.io.switch        <> io.switch
    u_sub1.io.switch_reg    <> io.led_blue
}

class Sub1() extends Component {

    // THIS ALSO CHANGES THE NAMES FOR THE DOMAIN ABOVE !!!
    ClockDomain.current.clock.setName("sub_clk")
    ClockDomain.current.reset.setName("sub_reset_")

    val io = new Bundle {
        val switch          = in(Bool)
        val switch_reg      = out(Bool)
    }

    io.switch_reg := RegNext(io.switch) init (False)
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
