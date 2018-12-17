// Generator : SpinalHDL v1.1.5    git head : 0310b2489a097f2b9de5535e02192d9ddd2764ae
// Date      : 17/12/2018, 00:40:48
// Component : Top


module ClkDiv2 (
      output  io_clk_div2,
      output  io_reset_div2_,
      input   vo_clk,
      input   vo_reset_);
  reg  clk_div2;
  reg  reset_div2_;
  assign io_clk_div2 = clk_div2;
  assign io_reset_div2_ = reset_div2_;
  always @ (posedge vo_clk or negedge vo_reset_) begin
    if (!vo_reset_) begin
      clk_div2 <= 1'b0;
      reset_div2_ <= 1'b0;
    end else begin
      clk_div2 <= (! clk_div2);
      reset_div2_ <= (clk_div2 || reset_div2_);
    end
  end

endmodule

module Sub1 (
      input   io_switch,
      output  io_switch_reg,
      input   vo_div2_clk,
      input   vo_div2_reset_);
  reg  _zz_1;
  assign io_switch_reg = _zz_1;
  always @ (posedge vo_div2_clk or negedge vo_div2_reset_) begin
    if (!vo_div2_reset_) begin
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
      input   vo_reset_);
  wire  _zz_3;
  wire  _zz_4;
  wire  _zz_5;
  wire  vo_domain_vo_div2_clk;
  wire  vo_domain_vo_div2_reset_;
  wire  vo_div2_clk;
  wire  vo_div2_reset_;
  reg  _zz_1;
  reg  _zz_2;
  ClkDiv2 vo_domain_u_clk_div2 ( 
    .io_clk_div2(_zz_3),
    .io_reset_div2_(_zz_4),
    .vo_clk(vo_clk),
    .vo_reset_(vo_reset_) 
  );
  Sub1 vo_div2_domain_u_sub1 ( 
    .io_switch(io_switch),
    .io_switch_reg(_zz_5),
    .vo_div2_clk(vo_div2_clk),
    .vo_div2_reset_(vo_div2_reset_) 
  );
  assign vo_domain_vo_div2_clk = _zz_3;
  assign vo_domain_vo_div2_reset_ = _zz_4;
  assign vo_div2_clk = vo_domain_vo_div2_clk;
  assign vo_div2_reset_ = vo_domain_vo_div2_reset_;
  assign io_led_red = _zz_1;
  assign io_led_green = _zz_2;
  assign io_led_blue = _zz_5;
  always @ (posedge vo_div2_clk or negedge vo_div2_reset_) begin
    if (!vo_div2_reset_) begin
      _zz_1 <= 1'b0;
    end else begin
      _zz_1 <= io_switch;
    end
  end

  always @ (posedge vo_div2_clk) begin
    _zz_2 <= io_switch;
  end

endmodule

