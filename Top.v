// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 17/12/2018, 03:46:13
// Component : Top


module Top (
      output  io_led_red,
      output  io_led_green,
      input   io_switch,
      input   clk,
      input   resetn);
  reg  _zz_1;
  reg  _zz_2;
  assign io_led_red = _zz_1;
  assign io_led_green = _zz_2;
  always @ (posedge clk or negedge resetn) begin
    if (!resetn) begin
      _zz_1 <= 1'b0;
    end else begin
      _zz_1 <= io_switch;
    end
  end

  always @ (posedge clk) begin
    _zz_2 <= io_switch;
  end

endmodule

