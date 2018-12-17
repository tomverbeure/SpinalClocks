// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 17/12/2018, 00:56:50
// Component : Top


module Sub1 (
      input   io_switch,
      output  io_switch_reg,
      input   vo_clk,
      input   vo_reset_,
      input   _zz_2);
  reg  _zz_1;
  assign io_switch_reg = _zz_1;
  always @ (posedge vo_clk or negedge vo_reset_) begin
    if (!vo_reset_) begin
      _zz_1 <= 1'b0;
    end else begin
      if(_zz_2) begin
        _zz_1 <= io_switch;
      end
    end
  end

endmodule

module Top (
      output  io_led_red,
      output  io_led_green,
      output  io_led_blue,
      input   io_switch,
      input   vo_clk,
      input   vo_reset_);
  wire  _zz_6;
  reg [1:0] _zz_1;
  wire  _zz_2;
  reg  _zz_3;
  reg  _zz_4;
  reg  _zz_5;
  Sub1 vo_div2_domain_u_sub1 ( 
    .io_switch(io_switch),
    .io_switch_reg(_zz_6),
    .vo_clk(vo_clk),
    .vo_reset_(vo_reset_),
    ._zz_2(_zz_3) 
  );
  assign _zz_2 = (_zz_1 == (2'b10));
  assign io_led_red = _zz_4;
  assign io_led_blue = _zz_6;
  assign io_led_green = _zz_5;
  always @ (posedge vo_clk or negedge vo_reset_) begin
    if (!vo_reset_) begin
      _zz_1 <= (2'b00);
      _zz_3 <= 1'b0;
      _zz_5 <= 1'b0;
    end else begin
      _zz_1 <= (_zz_1 + (2'b01));
      if(_zz_2)begin
        _zz_1 <= (2'b00);
      end
      _zz_3 <= _zz_2;
      _zz_5 <= io_switch;
    end
  end

  always @ (posedge vo_clk or negedge vo_reset_) begin
    if (!vo_reset_) begin
      _zz_4 <= 1'b0;
    end else begin
      if(_zz_3) begin
        _zz_4 <= io_switch;
      end
    end
  end

endmodule

