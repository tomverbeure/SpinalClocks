// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 17/12/2018, 03:47:50
// Component : Top


module Sub1 (
      input   io_switch,
      output  io_switch_reg,
      input   vo_clk,
      input   vo_resetn);
  reg  _zz_1;
  assign io_switch_reg = _zz_1;
  always @ (posedge vo_clk or negedge vo_resetn) begin
    if (!vo_resetn) begin
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
      input   vo_clk,
      input   vo_resetn);
  wire  _zz_3;
  reg  _zz_1;
  reg  _zz_2;
  Sub1 vo_domain_u_sub1 ( 
    .io_switch(io_switch),
    .io_switch_reg(_zz_3),
    .vo_clk(vo_clk),
    .vo_resetn(vo_resetn) 
  );
  assign io_led_red = _zz_1;
  assign io_led_green = _zz_2;
  assign io_led_blue = _zz_3;
  always @ (posedge vo_clk or negedge vo_resetn) begin
    if (!vo_resetn) begin
      _zz_1 <= 1'b0;
    end else begin
      _zz_1 <= io_switch;
    end
  end

  always @ (posedge vo_clk) begin
    _zz_2 <= io_switch;
  end

endmodule

