
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

    // Use the VO clock domain to create 2 new signals: vo_div2_clk and vo_div2_reset_
    // These are currently ordinary signals and not yet understood to be clock or reset.
    val vo_domain = new ClockingArea(voClkDomain) {
        val vo_div2_clk     = Bool
        val vo_div2_reset_  = Bool

        val u_clk_div2 = new ClkDiv2
        u_clk_div2.io.clk_div2    <> vo_div2_clk
        u_clk_div2.io.reset_div2_ <> vo_div2_reset_
    }

    // Now create a new clock domain "vo_div2". The clock and reset of this domain will be internal signals.
    // It's up to the programmer to later tell exactly which internal signals will be used for that.
    val voDiv2ClkDomain = ClockDomain.internal("vo_div2")

    // Tell the clock domain which ordinary signals will be used as clock and reset. We are using the
    // earlier generated signals for this.
    // The 2 assignments below are mandatory: without them, the clock domain is underspecified and
    // SpinalHDL won't know which signals are used as clock and reset.
    voDiv2ClkDomain.clock := vo_domain.vo_div2_clk
    voDiv2ClkDomain.reset := vo_domain.vo_div2_reset_

    // Without this, the name of the reset signal will be 'vo_div2_resetn'
    voDiv2ClkDomain.reset.setName("vo_div2_reset_")

    // We can now use this new clock domain to drive regular logic.
    val vo_div2_domain = new ClockingArea(voDiv2ClkDomain) {

        io.led_red   := RegNext(io.switch) init(False)
        io.led_green := RegNext(io.switch)

        val u_sub1 = new Sub1
        u_sub1.io.switch        <> io.switch
        u_sub1.io.switch_reg    <> io.led_blue
    }

}

class ClkDiv2 extends Component {

    val io = new Bundle {
        val clk_div2       = out(Bool)
        val reset_div2_    = out(Bool)
    }

    val clk_div2        = Reg(Bool) init(False)
    val reset_div2_     = Reg(Bool) init(False)

    clk_div2    := ~clk_div2
    reset_div2_ := clk_div2 | reset_div2_

    io.clk_div2    := clk_div2
    io.reset_div2_ := reset_div2_
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
