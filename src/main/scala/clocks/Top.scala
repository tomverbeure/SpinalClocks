
package clocks

import spinal.core._
import spinal.lib._

class Top() extends Component {

    var io = new Bundle {
        var led_red         = out(Bool)
        var led_green       = out(Bool)
        var led_blue        = out(Bool)
        var switch          = in(Bool)
    }

    var voClkDomain = ClockDomain.external("vo")

    var vo_domain = new ClockingArea(voClkDomain) {
        io.led_red   := RegNext(io.switch) init(False)
        io.led_green := RegNext(io.switch)

        var u_sub1 = new Sub1
        u_sub1.io.switch        <> io.switch
        u_sub1.io.switch_reg    <> io.led_blue
    }
}

class Sub1() extends Component {

    var io = new Bundle {
        var switch          = in(Bool)
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
