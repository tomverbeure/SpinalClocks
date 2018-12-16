// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 16/12/2018, 23:42:11
// Component : Top


module Sub1 (
      input   io_switch,
      output  io_switch_reg,
      input   sub_clk,
      input   sub_reset_);
  reg  _zz_1;
  assign io_switch_reg = _zz_1;
  always @ (posedge sub_clk or negedge sub_reset_) begin
    if (!sub_reset_) begin
      _zz_1 <= 1'b0;
    end else begin
      _zz_1 <= io_switch;
    end
  end

endmodule

module Top (
      output  io_led_red,
      output  io_led_green,
      output  io_led_blue,
      input   io_switch,
      input   sub_clk,
      input   sub_reset_);
  wire  _zz_3;
  reg  _zz_1;
  reg  _zz_2;
  Sub1 u_sub1 ( 
    .io_switch(io_switch),
    .io_switch_reg(_zz_3),
    .sub_clk(sub_clk),
    .sub_reset_(sub_reset_) 
  );
  assign io_led_red = _zz_1;
  assign io_led_green = _zz_2;
  assign io_led_blue = _zz_3;
  always @ (posedge sub_clk or negedge sub_reset_) begin
    if (!sub_reset_) begin
      _zz_1 <= 1'b0;
    end else begin
      _zz_1 <= io_switch;
    end
  end

  always @ (posedge sub_clk) begin
    _zz_2 <= io_switch;
  end

endmodule

