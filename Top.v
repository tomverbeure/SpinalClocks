// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 17/12/2018, 03:35:14
// Component : Top


module Top (
      output  io_led_red,
      output  io_led_green,
      output  io_led_blue,
      input   io_switch,
      input   src_clk,
      input   src_reset_);
  reg  src_domain_src_switch;
  assign io_led_red = src_domain_src_switch;
  assign io_led_green = src_domain_src_switch;
  assign io_led_blue = src_domain_src_switch;
  always @ (posedge src_clk or negedge src_reset_) begin
    if (!src_reset_) begin
      src_domain_src_switch <= 1'b0;
    end else begin
      src_domain_src_switch <= io_switch;
    end
  end

endmodule

