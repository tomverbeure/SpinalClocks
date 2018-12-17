
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

    // Create a VO clock domain with clock and reset coming straight from the toplevel
    val voClkDomain = ClockDomain.external("vo")
    voClkDomain.reset.setName("vo_reset_")

    // "slowed down" clock domain. The FFs are still using the same "vo_clk" clock, but 
    // they are all conditionally gated by an enable that toggles once per 3 clock cycles.
    val voDiv2ClkDomain = voClkDomain.newClockDomainSlowedBy(3)

    // We can now use this new clock domain to drive regular logic.
    val vo_div2_domain = new ClockingArea(voDiv2ClkDomain) {

        io.led_red   := RegNext(io.switch) init(False)

        val u_sub1 = new Sub1
        u_sub1.io.switch        <> io.switch
        u_sub1.io.switch_reg    <> io.led_blue
    }

    val vo_domain = new ClockingArea(voClkDomain) {
        io.led_green := RegNext(io.switch) init(False)
    }
}

class Sub1() extends Component {

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
