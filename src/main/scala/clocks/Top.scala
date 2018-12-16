
package clocks

import spinal.core._
import spinal.lib._

class Top() extends Component {

    var io = new Bundle {
        var led_red         = out(Bool)
        var led_green       = out(Bool)
        var switch          = in(Bool)
    }

    io.led_red   := RegNext(io.switch) init(False)
    io.led_green := RegNext(io.switch)
}


object TopVerilog{
    def main(args: Array[String]) {

        val config = SpinalConfig()
        config.generateVerilog({
            val toplevel = new Top()
            toplevel
        })
        println("DONE")
    }
}
