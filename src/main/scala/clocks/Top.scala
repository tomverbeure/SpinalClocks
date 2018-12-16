
package clocks

import spinal.core._
import spinal.lib._

class Top() extends Component {

    var io = new Bundle {
        var led_green       = out(Bool)
        var switch          = in(Bool)
    }

    io.led_green := True
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
