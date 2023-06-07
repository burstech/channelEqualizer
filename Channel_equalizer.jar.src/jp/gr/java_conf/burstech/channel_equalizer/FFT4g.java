package jp.gr.java_conf.burstech.channel_equalizer;

class FFT4g {
  private int[] ip;
  
  private int n;
  
  private double[] w;
  
  FFT4g(int paramInt) {
    this.n = paramInt;
    this.ip = new int[(int)Math.sqrt(paramInt / 2.0D) + 2 + 1];
    this.w = new double[paramInt / 2];
    this.ip[0] = 0;
  }
  
  private void bitrv2(int paramInt, double[] paramArrayOfdouble) {
    this.ip[2] = 0;
    int i = paramInt;
    paramInt = 1;
    label36: while (true) {
      int j;
      if (paramInt << 3 >= i) {
        j = paramInt * 2;
        if (paramInt << 3 == i) {
          i = 0;
          label34: while (true) {
            if (i < paramInt) {
              for (int k = 0;; k++) {
                if (k >= i) {
                  int i2 = i * 2 + j + this.ip[i + 2];
                  k = i2 + j;
                  double d5 = paramArrayOfdouble[i2];
                  double d6 = paramArrayOfdouble[i2 + 1];
                  double d7 = paramArrayOfdouble[k];
                  double d8 = paramArrayOfdouble[k + 1];
                  paramArrayOfdouble[i2] = d7;
                  paramArrayOfdouble[i2 + 1] = d8;
                  paramArrayOfdouble[k] = d5;
                  paramArrayOfdouble[k + 1] = d6;
                  i++;
                  continue label34;
                } 
                int n = k * 2 + this.ip[i + 2];
                int m = i * 2 + this.ip[k + 2];
                double d1 = paramArrayOfdouble[n];
                double d4 = paramArrayOfdouble[n + 1];
                double d2 = paramArrayOfdouble[m];
                double d3 = paramArrayOfdouble[m + 1];
                paramArrayOfdouble[n] = d2;
                paramArrayOfdouble[n + 1] = d3;
                paramArrayOfdouble[m] = d1;
                paramArrayOfdouble[m + 1] = d4;
                int i1 = n + j;
                n = m + j * 2;
                d2 = paramArrayOfdouble[i1];
                d4 = paramArrayOfdouble[i1 + 1];
                d1 = paramArrayOfdouble[n];
                d3 = paramArrayOfdouble[n + 1];
                paramArrayOfdouble[i1] = d1;
                paramArrayOfdouble[i1 + 1] = d3;
                paramArrayOfdouble[n] = d2;
                paramArrayOfdouble[n + 1] = d4;
                m = i1 + j;
                n -= j;
                d1 = paramArrayOfdouble[m];
                d4 = paramArrayOfdouble[m + 1];
                d3 = paramArrayOfdouble[n];
                d2 = paramArrayOfdouble[n + 1];
                paramArrayOfdouble[m] = d3;
                paramArrayOfdouble[m + 1] = d2;
                paramArrayOfdouble[n] = d1;
                paramArrayOfdouble[n + 1] = d4;
                m += j;
                n += j * 2;
                d4 = paramArrayOfdouble[m];
                d1 = paramArrayOfdouble[m + 1];
                d2 = paramArrayOfdouble[n];
                d3 = paramArrayOfdouble[n + 1];
                paramArrayOfdouble[m] = d2;
                paramArrayOfdouble[m + 1] = d3;
                paramArrayOfdouble[n] = d4;
                paramArrayOfdouble[n + 1] = d1;
              } 
              break;
            } 
            return;
          } 
          break;
        } 
      } else {
        int k = i >> 1;
        for (i = 0;; i++) {
          if (i >= paramInt) {
            paramInt <<= 1;
            i = k;
            continue label36;
          } 
          this.ip[paramInt + 2 + i] = this.ip[i + 2] + k;
        } 
        break;
      } 
      i = 1;
      label35: while (true) {
        if (i < paramInt) {
          for (byte b = 0;; b++) {
            if (b >= i) {
              i++;
              continue label35;
            } 
            int k = b * 2 + this.ip[i + 2];
            int m = i * 2 + this.ip[b + 2];
            double d1 = paramArrayOfdouble[k];
            double d3 = paramArrayOfdouble[k + 1];
            double d4 = paramArrayOfdouble[m];
            double d2 = paramArrayOfdouble[m + 1];
            paramArrayOfdouble[k] = d4;
            paramArrayOfdouble[k + 1] = d2;
            paramArrayOfdouble[m] = d1;
            paramArrayOfdouble[m + 1] = d3;
            k += j;
            m += j;
            d4 = paramArrayOfdouble[k];
            d2 = paramArrayOfdouble[k + 1];
            d3 = paramArrayOfdouble[m];
            d1 = paramArrayOfdouble[m + 1];
            paramArrayOfdouble[k] = d3;
            paramArrayOfdouble[k + 1] = d1;
            paramArrayOfdouble[m] = d4;
            paramArrayOfdouble[m + 1] = d2;
          } 
          break;
        } 
        return;
      } 
      break;
    } 
  }
  
  private void cft1st(double[] paramArrayOfdouble) {
    double d1 = paramArrayOfdouble[0] + paramArrayOfdouble[2];
    double d2 = paramArrayOfdouble[1] + paramArrayOfdouble[3];
    double d3 = paramArrayOfdouble[0] - paramArrayOfdouble[2];
    double d4 = paramArrayOfdouble[1] - paramArrayOfdouble[3];
    double d5 = paramArrayOfdouble[4] + paramArrayOfdouble[6];
    double d6 = paramArrayOfdouble[5] + paramArrayOfdouble[7];
    double d7 = paramArrayOfdouble[4] - paramArrayOfdouble[6];
    double d8 = paramArrayOfdouble[5] - paramArrayOfdouble[7];
    paramArrayOfdouble[0] = d1 + d5;
    paramArrayOfdouble[1] = d2 + d6;
    paramArrayOfdouble[4] = d1 - d5;
    paramArrayOfdouble[5] = d2 - d6;
    paramArrayOfdouble[2] = d3 - d8;
    paramArrayOfdouble[3] = d4 + d7;
    paramArrayOfdouble[6] = d3 + d8;
    paramArrayOfdouble[7] = d4 - d7;
    d5 = this.w[2];
    d1 = paramArrayOfdouble[8] + paramArrayOfdouble[10];
    d4 = paramArrayOfdouble[9] + paramArrayOfdouble[11];
    d7 = paramArrayOfdouble[8] - paramArrayOfdouble[10];
    d2 = paramArrayOfdouble[9] - paramArrayOfdouble[11];
    double d9 = paramArrayOfdouble[12] + paramArrayOfdouble[14];
    d3 = paramArrayOfdouble[13] + paramArrayOfdouble[15];
    d8 = paramArrayOfdouble[12] - paramArrayOfdouble[14];
    d6 = paramArrayOfdouble[13] - paramArrayOfdouble[15];
    paramArrayOfdouble[8] = d1 + d9;
    paramArrayOfdouble[9] = d4 + d3;
    paramArrayOfdouble[12] = d3 - d4;
    paramArrayOfdouble[13] = d1 - d9;
    d4 = d7 - d6;
    d3 = d2 + d8;
    paramArrayOfdouble[10] = (d4 - d3) * d5;
    paramArrayOfdouble[11] = (d4 + d3) * d5;
    d6 += d7;
    d2 = d8 - d2;
    paramArrayOfdouble[14] = (d2 - d6) * d5;
    paramArrayOfdouble[15] = (d2 + d6) * d5;
    byte b1 = 0;
    for (byte b2 = 16;; b2 += 16) {
      if (b2 >= this.n)
        return; 
      b1 += true;
      int i = b1 * 2;
      d2 = this.w[b1];
      d5 = this.w[b1 + 1];
      d1 = this.w[i];
      double d10 = this.w[i + 1];
      d6 = d1 - 2.0D * d5 * d10;
      d8 = 2.0D * d5 * d1 - d10;
      double d11 = paramArrayOfdouble[b2] + paramArrayOfdouble[b2 + 2];
      double d12 = paramArrayOfdouble[b2 + 1] + paramArrayOfdouble[b2 + 3];
      d9 = paramArrayOfdouble[b2] - paramArrayOfdouble[b2 + 2];
      d4 = paramArrayOfdouble[b2 + 1] - paramArrayOfdouble[b2 + 3];
      double d13 = paramArrayOfdouble[b2 + 4] + paramArrayOfdouble[b2 + 6];
      double d14 = paramArrayOfdouble[b2 + 5] + paramArrayOfdouble[b2 + 7];
      d7 = paramArrayOfdouble[b2 + 4] - paramArrayOfdouble[b2 + 6];
      d3 = paramArrayOfdouble[b2 + 5] - paramArrayOfdouble[b2 + 7];
      paramArrayOfdouble[b2] = d11 + d13;
      paramArrayOfdouble[b2 + 1] = d12 + d14;
      d11 -= d13;
      d14 = d12 - d14;
      paramArrayOfdouble[b2 + 4] = d2 * d11 - d5 * d14;
      paramArrayOfdouble[b2 + 5] = d2 * d14 + d5 * d11;
      d12 = d9 - d3;
      d14 = d4 + d7;
      paramArrayOfdouble[b2 + 2] = d1 * d12 - d10 * d14;
      paramArrayOfdouble[b2 + 3] = d1 * d14 + d10 * d12;
      d3 = d9 + d3;
      d7 = d4 - d7;
      paramArrayOfdouble[b2 + 6] = d6 * d3 - d8 * d7;
      paramArrayOfdouble[b2 + 7] = d6 * d7 + d8 * d3;
      d10 = this.w[i + 2];
      d1 = this.w[i + 3];
      d6 = d10 - 2.0D * d2 * d1;
      d8 = 2.0D * d2 * d10 - d1;
      d13 = paramArrayOfdouble[b2 + 8] + paramArrayOfdouble[b2 + 10];
      d12 = paramArrayOfdouble[b2 + 9] + paramArrayOfdouble[b2 + 11];
      d3 = paramArrayOfdouble[b2 + 8] - paramArrayOfdouble[b2 + 10];
      d4 = paramArrayOfdouble[b2 + 9] - paramArrayOfdouble[b2 + 11];
      d11 = paramArrayOfdouble[b2 + 12] + paramArrayOfdouble[b2 + 14];
      d14 = paramArrayOfdouble[b2 + 13] + paramArrayOfdouble[b2 + 15];
      d7 = paramArrayOfdouble[b2 + 12] - paramArrayOfdouble[b2 + 14];
      d9 = paramArrayOfdouble[b2 + 13] - paramArrayOfdouble[b2 + 15];
      paramArrayOfdouble[b2 + 8] = d13 + d11;
      paramArrayOfdouble[b2 + 9] = d12 + d14;
      d11 = d13 - d11;
      d14 = d12 - d14;
      paramArrayOfdouble[b2 + 12] = -d5 * d11 - d2 * d14;
      paramArrayOfdouble[b2 + 13] = -d5 * d14 + d2 * d11;
      d2 = d3 - d9;
      d5 = d4 + d7;
      paramArrayOfdouble[b2 + 10] = d10 * d2 - d1 * d5;
      paramArrayOfdouble[b2 + 11] = d10 * d5 + d1 * d2;
      d5 = d3 + d9;
      d2 = d4 - d7;
      paramArrayOfdouble[b2 + 14] = d6 * d5 - d8 * d2;
      paramArrayOfdouble[b2 + 15] = d6 * d2 + d8 * d5;
    } 
  }
  
  private void cftbsub(double[] paramArrayOfdouble) {
    // Byte code:
    //   0: iconst_2
    //   1: istore_2
    //   2: aload_0
    //   3: getfield n : I
    //   6: bipush #8
    //   8: if_icmple -> 29
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial cft1st : ([D)V
    //   16: bipush #8
    //   18: istore_2
    //   19: iload_2
    //   20: iconst_2
    //   21: ishl
    //   22: aload_0
    //   23: getfield n : I
    //   26: if_icmplt -> 47
    //   29: iload_2
    //   30: iconst_2
    //   31: ishl
    //   32: aload_0
    //   33: getfield n : I
    //   36: if_icmpne -> 263
    //   39: iconst_0
    //   40: istore_3
    //   41: iload_3
    //   42: iload_2
    //   43: if_icmplt -> 60
    //   46: return
    //   47: aload_0
    //   48: iload_2
    //   49: aload_1
    //   50: invokespecial cftmdl : (I[D)V
    //   53: iload_2
    //   54: iconst_2
    //   55: ishl
    //   56: istore_2
    //   57: goto -> 19
    //   60: iload_3
    //   61: iload_2
    //   62: iadd
    //   63: istore #4
    //   65: iload #4
    //   67: iload_2
    //   68: iadd
    //   69: istore #5
    //   71: iload #5
    //   73: iload_2
    //   74: iadd
    //   75: istore #6
    //   77: aload_1
    //   78: iload_3
    //   79: daload
    //   80: aload_1
    //   81: iload #4
    //   83: daload
    //   84: dadd
    //   85: dstore #7
    //   87: aload_1
    //   88: iload_3
    //   89: iconst_1
    //   90: iadd
    //   91: daload
    //   92: dneg
    //   93: aload_1
    //   94: iload #4
    //   96: iconst_1
    //   97: iadd
    //   98: daload
    //   99: dsub
    //   100: dstore #9
    //   102: aload_1
    //   103: iload_3
    //   104: daload
    //   105: aload_1
    //   106: iload #4
    //   108: daload
    //   109: dsub
    //   110: dstore #11
    //   112: aload_1
    //   113: iload_3
    //   114: iconst_1
    //   115: iadd
    //   116: daload
    //   117: dneg
    //   118: aload_1
    //   119: iload #4
    //   121: iconst_1
    //   122: iadd
    //   123: daload
    //   124: dadd
    //   125: dstore #13
    //   127: aload_1
    //   128: iload #5
    //   130: daload
    //   131: aload_1
    //   132: iload #6
    //   134: daload
    //   135: dadd
    //   136: dstore #15
    //   138: aload_1
    //   139: iload #5
    //   141: iconst_1
    //   142: iadd
    //   143: daload
    //   144: aload_1
    //   145: iload #6
    //   147: iconst_1
    //   148: iadd
    //   149: daload
    //   150: dadd
    //   151: dstore #17
    //   153: aload_1
    //   154: iload #5
    //   156: daload
    //   157: aload_1
    //   158: iload #6
    //   160: daload
    //   161: dsub
    //   162: dstore #19
    //   164: aload_1
    //   165: iload #5
    //   167: iconst_1
    //   168: iadd
    //   169: daload
    //   170: aload_1
    //   171: iload #6
    //   173: iconst_1
    //   174: iadd
    //   175: daload
    //   176: dsub
    //   177: dstore #21
    //   179: aload_1
    //   180: iload_3
    //   181: dload #7
    //   183: dload #15
    //   185: dadd
    //   186: dastore
    //   187: aload_1
    //   188: iload_3
    //   189: iconst_1
    //   190: iadd
    //   191: dload #9
    //   193: dload #17
    //   195: dsub
    //   196: dastore
    //   197: aload_1
    //   198: iload #5
    //   200: dload #7
    //   202: dload #15
    //   204: dsub
    //   205: dastore
    //   206: aload_1
    //   207: iload #5
    //   209: iconst_1
    //   210: iadd
    //   211: dload #9
    //   213: dload #17
    //   215: dadd
    //   216: dastore
    //   217: aload_1
    //   218: iload #4
    //   220: dload #11
    //   222: dload #21
    //   224: dsub
    //   225: dastore
    //   226: aload_1
    //   227: iload #4
    //   229: iconst_1
    //   230: iadd
    //   231: dload #13
    //   233: dload #19
    //   235: dsub
    //   236: dastore
    //   237: aload_1
    //   238: iload #6
    //   240: dload #11
    //   242: dload #21
    //   244: dadd
    //   245: dastore
    //   246: aload_1
    //   247: iload #6
    //   249: iconst_1
    //   250: iadd
    //   251: dload #13
    //   253: dload #19
    //   255: dadd
    //   256: dastore
    //   257: iinc #3, 2
    //   260: goto -> 41
    //   263: iconst_0
    //   264: istore_3
    //   265: iload_3
    //   266: iload_2
    //   267: if_icmpge -> 46
    //   270: iload_3
    //   271: iload_2
    //   272: iadd
    //   273: istore #4
    //   275: aload_1
    //   276: iload_3
    //   277: daload
    //   278: dstore #11
    //   280: aload_1
    //   281: iload #4
    //   283: daload
    //   284: dstore #13
    //   286: aload_1
    //   287: iload_3
    //   288: iconst_1
    //   289: iadd
    //   290: daload
    //   291: dneg
    //   292: dstore #7
    //   294: aload_1
    //   295: iload #4
    //   297: iconst_1
    //   298: iadd
    //   299: daload
    //   300: dstore #21
    //   302: aload_1
    //   303: iload_3
    //   304: aload_1
    //   305: iload_3
    //   306: daload
    //   307: aload_1
    //   308: iload #4
    //   310: daload
    //   311: dadd
    //   312: dastore
    //   313: aload_1
    //   314: iload_3
    //   315: iconst_1
    //   316: iadd
    //   317: aload_1
    //   318: iload_3
    //   319: iconst_1
    //   320: iadd
    //   321: daload
    //   322: dneg
    //   323: aload_1
    //   324: iload #4
    //   326: iconst_1
    //   327: iadd
    //   328: daload
    //   329: dsub
    //   330: dastore
    //   331: aload_1
    //   332: iload #4
    //   334: dload #11
    //   336: dload #13
    //   338: dsub
    //   339: dastore
    //   340: aload_1
    //   341: iload #4
    //   343: iconst_1
    //   344: iadd
    //   345: dload #7
    //   347: dload #21
    //   349: dadd
    //   350: dastore
    //   351: iinc #3, 2
    //   354: goto -> 265
  }
  
  private void cftfsub(double[] paramArrayOfdouble) {
    // Byte code:
    //   0: iconst_2
    //   1: istore_2
    //   2: aload_0
    //   3: getfield n : I
    //   6: bipush #8
    //   8: if_icmple -> 29
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial cft1st : ([D)V
    //   16: bipush #8
    //   18: istore_2
    //   19: iload_2
    //   20: iconst_2
    //   21: ishl
    //   22: aload_0
    //   23: getfield n : I
    //   26: if_icmplt -> 47
    //   29: iload_2
    //   30: iconst_2
    //   31: ishl
    //   32: aload_0
    //   33: getfield n : I
    //   36: if_icmpne -> 261
    //   39: iconst_0
    //   40: istore_3
    //   41: iload_3
    //   42: iload_2
    //   43: if_icmplt -> 60
    //   46: return
    //   47: aload_0
    //   48: iload_2
    //   49: aload_1
    //   50: invokespecial cftmdl : (I[D)V
    //   53: iload_2
    //   54: iconst_2
    //   55: ishl
    //   56: istore_2
    //   57: goto -> 19
    //   60: iload_3
    //   61: iload_2
    //   62: iadd
    //   63: istore #4
    //   65: iload #4
    //   67: iload_2
    //   68: iadd
    //   69: istore #5
    //   71: iload #5
    //   73: iload_2
    //   74: iadd
    //   75: istore #6
    //   77: aload_1
    //   78: iload_3
    //   79: daload
    //   80: aload_1
    //   81: iload #4
    //   83: daload
    //   84: dadd
    //   85: dstore #7
    //   87: aload_1
    //   88: iload_3
    //   89: iconst_1
    //   90: iadd
    //   91: daload
    //   92: aload_1
    //   93: iload #4
    //   95: iconst_1
    //   96: iadd
    //   97: daload
    //   98: dadd
    //   99: dstore #9
    //   101: aload_1
    //   102: iload_3
    //   103: daload
    //   104: aload_1
    //   105: iload #4
    //   107: daload
    //   108: dsub
    //   109: dstore #11
    //   111: aload_1
    //   112: iload_3
    //   113: iconst_1
    //   114: iadd
    //   115: daload
    //   116: aload_1
    //   117: iload #4
    //   119: iconst_1
    //   120: iadd
    //   121: daload
    //   122: dsub
    //   123: dstore #13
    //   125: aload_1
    //   126: iload #5
    //   128: daload
    //   129: aload_1
    //   130: iload #6
    //   132: daload
    //   133: dadd
    //   134: dstore #15
    //   136: aload_1
    //   137: iload #5
    //   139: iconst_1
    //   140: iadd
    //   141: daload
    //   142: aload_1
    //   143: iload #6
    //   145: iconst_1
    //   146: iadd
    //   147: daload
    //   148: dadd
    //   149: dstore #17
    //   151: aload_1
    //   152: iload #5
    //   154: daload
    //   155: aload_1
    //   156: iload #6
    //   158: daload
    //   159: dsub
    //   160: dstore #19
    //   162: aload_1
    //   163: iload #5
    //   165: iconst_1
    //   166: iadd
    //   167: daload
    //   168: aload_1
    //   169: iload #6
    //   171: iconst_1
    //   172: iadd
    //   173: daload
    //   174: dsub
    //   175: dstore #21
    //   177: aload_1
    //   178: iload_3
    //   179: dload #7
    //   181: dload #15
    //   183: dadd
    //   184: dastore
    //   185: aload_1
    //   186: iload_3
    //   187: iconst_1
    //   188: iadd
    //   189: dload #9
    //   191: dload #17
    //   193: dadd
    //   194: dastore
    //   195: aload_1
    //   196: iload #5
    //   198: dload #7
    //   200: dload #15
    //   202: dsub
    //   203: dastore
    //   204: aload_1
    //   205: iload #5
    //   207: iconst_1
    //   208: iadd
    //   209: dload #9
    //   211: dload #17
    //   213: dsub
    //   214: dastore
    //   215: aload_1
    //   216: iload #4
    //   218: dload #11
    //   220: dload #21
    //   222: dsub
    //   223: dastore
    //   224: aload_1
    //   225: iload #4
    //   227: iconst_1
    //   228: iadd
    //   229: dload #13
    //   231: dload #19
    //   233: dadd
    //   234: dastore
    //   235: aload_1
    //   236: iload #6
    //   238: dload #11
    //   240: dload #21
    //   242: dadd
    //   243: dastore
    //   244: aload_1
    //   245: iload #6
    //   247: iconst_1
    //   248: iadd
    //   249: dload #13
    //   251: dload #19
    //   253: dsub
    //   254: dastore
    //   255: iinc #3, 2
    //   258: goto -> 41
    //   261: iconst_0
    //   262: istore_3
    //   263: iload_3
    //   264: iload_2
    //   265: if_icmpge -> 46
    //   268: iload_3
    //   269: iload_2
    //   270: iadd
    //   271: istore #4
    //   273: aload_1
    //   274: iload_3
    //   275: daload
    //   276: dstore #17
    //   278: aload_1
    //   279: iload #4
    //   281: daload
    //   282: dstore #13
    //   284: aload_1
    //   285: iload_3
    //   286: iconst_1
    //   287: iadd
    //   288: daload
    //   289: dstore #19
    //   291: aload_1
    //   292: iload #4
    //   294: iconst_1
    //   295: iadd
    //   296: daload
    //   297: dstore #9
    //   299: aload_1
    //   300: iload_3
    //   301: aload_1
    //   302: iload_3
    //   303: daload
    //   304: aload_1
    //   305: iload #4
    //   307: daload
    //   308: dadd
    //   309: dastore
    //   310: iload_3
    //   311: iconst_1
    //   312: iadd
    //   313: istore #5
    //   315: aload_1
    //   316: iload #5
    //   318: aload_1
    //   319: iload #5
    //   321: daload
    //   322: aload_1
    //   323: iload #4
    //   325: iconst_1
    //   326: iadd
    //   327: daload
    //   328: dadd
    //   329: dastore
    //   330: aload_1
    //   331: iload #4
    //   333: dload #17
    //   335: dload #13
    //   337: dsub
    //   338: dastore
    //   339: aload_1
    //   340: iload #4
    //   342: iconst_1
    //   343: iadd
    //   344: dload #19
    //   346: dload #9
    //   348: dsub
    //   349: dastore
    //   350: iinc #3, 2
    //   353: goto -> 263
  }
  
  private void cftmdl(int paramInt, double[] paramArrayOfdouble) {
    int i = paramInt << 2;
    for (int j = 0;; j += 2) {
      if (j >= paramInt) {
        double d = this.w[2];
        for (j = i;; j += 2) {
          if (j >= paramInt + i) {
            int i4 = 0;
            int i5 = i * 2;
            j = i5;
            label25: while (true) {
              if (j >= this.n)
                return; 
              int i6 = i4 + 2;
              int i7 = i6 * 2;
              double d19 = this.w[i6];
              d = this.w[i6 + 1];
              double d17 = this.w[i7];
              double d21 = this.w[i7 + 1];
              double d20 = d17 - 2.0D * d * d21;
              double d18 = 2.0D * d * d17 - d21;
              for (i4 = j;; i4 += 2) {
                if (i4 >= paramInt + j) {
                  d18 = this.w[i7 + 2];
                  d20 = this.w[i7 + 3];
                  d21 = d18 - 2.0D * d19 * d20;
                  d17 = 2.0D * d19 * d18 - d20;
                  for (i4 = j + i;; i4 += 2) {
                    if (i4 >= j + i + paramInt) {
                      j += i5;
                      i4 = i6;
                      continue label25;
                    } 
                    i7 = i4 + paramInt;
                    int i11 = i7 + paramInt;
                    int i12 = i11 + paramInt;
                    double d33 = paramArrayOfdouble[i4] + paramArrayOfdouble[i7];
                    double d34 = paramArrayOfdouble[i4 + 1] + paramArrayOfdouble[i7 + 1];
                    double d37 = paramArrayOfdouble[i4] - paramArrayOfdouble[i7];
                    double d30 = paramArrayOfdouble[i4 + 1] - paramArrayOfdouble[i7 + 1];
                    double d35 = paramArrayOfdouble[i11] + paramArrayOfdouble[i12];
                    double d36 = paramArrayOfdouble[i11 + 1] + paramArrayOfdouble[i12 + 1];
                    double d31 = paramArrayOfdouble[i11] - paramArrayOfdouble[i12];
                    double d32 = paramArrayOfdouble[i11 + 1] - paramArrayOfdouble[i12 + 1];
                    paramArrayOfdouble[i4] = d33 + d35;
                    paramArrayOfdouble[i4 + 1] = d34 + d36;
                    d33 -= d35;
                    d36 = d34 - d36;
                    paramArrayOfdouble[i11] = -d * d33 - d19 * d36;
                    paramArrayOfdouble[i11 + 1] = -d * d36 + d19 * d33;
                    d34 = d37 - d32;
                    d36 = d30 + d31;
                    paramArrayOfdouble[i7] = d18 * d34 - d20 * d36;
                    paramArrayOfdouble[i7 + 1] = d18 * d36 + d20 * d34;
                    d32 = d37 + d32;
                    d31 = d30 - d31;
                    paramArrayOfdouble[i12] = d21 * d32 - d17 * d31;
                    paramArrayOfdouble[i12 + 1] = d21 * d31 + d17 * d32;
                  } 
                  break;
                } 
                int i8 = i4 + paramInt;
                int i9 = i8 + paramInt;
                int i10 = i9 + paramInt;
                double d25 = paramArrayOfdouble[i4] + paramArrayOfdouble[i8];
                double d26 = paramArrayOfdouble[i4 + 1] + paramArrayOfdouble[i8 + 1];
                double d24 = paramArrayOfdouble[i4] - paramArrayOfdouble[i8];
                double d23 = paramArrayOfdouble[i4 + 1] - paramArrayOfdouble[i8 + 1];
                double d27 = paramArrayOfdouble[i9] + paramArrayOfdouble[i10];
                double d28 = paramArrayOfdouble[i9 + 1] + paramArrayOfdouble[i10 + 1];
                double d22 = paramArrayOfdouble[i9] - paramArrayOfdouble[i10];
                double d29 = paramArrayOfdouble[i9 + 1] - paramArrayOfdouble[i10 + 1];
                paramArrayOfdouble[i4] = d25 + d27;
                paramArrayOfdouble[i4 + 1] = d26 + d28;
                d25 -= d27;
                d28 = d26 - d28;
                paramArrayOfdouble[i9] = d19 * d25 - d * d28;
                paramArrayOfdouble[i9 + 1] = d19 * d28 + d * d25;
                d26 = d24 - d29;
                d28 = d23 + d22;
                paramArrayOfdouble[i8] = d17 * d26 - d21 * d28;
                paramArrayOfdouble[i8 + 1] = d17 * d28 + d21 * d26;
                d24 += d29;
                d23 -= d22;
                paramArrayOfdouble[i10] = d20 * d24 - d18 * d23;
                paramArrayOfdouble[i10 + 1] = d20 * d23 + d18 * d24;
              } 
              break;
            } 
            break;
          } 
          int i3 = j + paramInt;
          int i1 = i3 + paramInt;
          int i2 = i1 + paramInt;
          double d12 = paramArrayOfdouble[j] + paramArrayOfdouble[i3];
          double d14 = paramArrayOfdouble[j + 1] + paramArrayOfdouble[i3 + 1];
          double d9 = paramArrayOfdouble[j] - paramArrayOfdouble[i3];
          double d15 = paramArrayOfdouble[j + 1] - paramArrayOfdouble[i3 + 1];
          double d10 = paramArrayOfdouble[i1] + paramArrayOfdouble[i2];
          double d16 = paramArrayOfdouble[i1 + 1] + paramArrayOfdouble[i2 + 1];
          double d13 = paramArrayOfdouble[i1] - paramArrayOfdouble[i2];
          double d11 = paramArrayOfdouble[i1 + 1] - paramArrayOfdouble[i2 + 1];
          paramArrayOfdouble[j] = d12 + d10;
          paramArrayOfdouble[j + 1] = d14 + d16;
          paramArrayOfdouble[i1] = d16 - d14;
          paramArrayOfdouble[i1 + 1] = d12 - d10;
          d14 = d9 - d11;
          d12 = d15 + d13;
          paramArrayOfdouble[i3] = (d14 - d12) * d;
          paramArrayOfdouble[i3 + 1] = (d14 + d12) * d;
          d11 += d9;
          d13 -= d15;
          paramArrayOfdouble[i2] = (d13 - d11) * d;
          paramArrayOfdouble[i2 + 1] = (d13 + d11) * d;
        } 
        break;
      } 
      int k = j + paramInt;
      int n = k + paramInt;
      int m = n + paramInt;
      double d2 = paramArrayOfdouble[j] + paramArrayOfdouble[k];
      double d3 = paramArrayOfdouble[j + 1] + paramArrayOfdouble[k + 1];
      double d4 = paramArrayOfdouble[j] - paramArrayOfdouble[k];
      double d5 = paramArrayOfdouble[j + 1] - paramArrayOfdouble[k + 1];
      double d6 = paramArrayOfdouble[n] + paramArrayOfdouble[m];
      double d7 = paramArrayOfdouble[n + 1] + paramArrayOfdouble[m + 1];
      double d8 = paramArrayOfdouble[n] - paramArrayOfdouble[m];
      double d1 = paramArrayOfdouble[n + 1] - paramArrayOfdouble[m + 1];
      paramArrayOfdouble[j] = d2 + d6;
      paramArrayOfdouble[j + 1] = d3 + d7;
      paramArrayOfdouble[n] = d2 - d6;
      paramArrayOfdouble[n + 1] = d3 - d7;
      paramArrayOfdouble[k] = d4 - d1;
      paramArrayOfdouble[k + 1] = d5 + d8;
      paramArrayOfdouble[m] = d4 + d1;
      paramArrayOfdouble[m + 1] = d5 - d8;
    } 
  }
  
  private void makewt(int paramInt) {
    this.ip[0] = paramInt;
    this.ip[1] = 1;
    if (paramInt > 2) {
      int i = paramInt >> 1;
      double d = Math.atan(1.0D) / i;
      this.w[0] = 1.0D;
      this.w[1] = 0.0D;
      this.w[i] = Math.cos(i * d);
      this.w[i + 1] = this.w[i];
      if (i > 2)
        for (byte b = 2;; b += 2) {
          if (b >= i) {
            bitrv2(paramInt, this.w);
            return;
          } 
          double d1 = Math.cos(b * d);
          double d2 = Math.sin(b * d);
          this.w[b] = d1;
          this.w[b + 1] = d2;
          this.w[paramInt - b] = d2;
          this.w[paramInt - b + 1] = d1;
        }  
    } 
  }
  
  private void rftbsub(double[] paramArrayOfdouble1, int paramInt1, double[] paramArrayOfdouble2, int paramInt2) {
    paramArrayOfdouble1[1] = -paramArrayOfdouble1[1];
    int i = this.n >> 1;
    int j = paramInt1 * 2 / i;
    int k = 0;
    for (byte b = 2;; b += 2) {
      if (b >= i) {
        paramArrayOfdouble1[i + 1] = -paramArrayOfdouble1[i + 1];
        return;
      } 
      int m = this.n - b;
      k += j;
      double d1 = 0.5D - paramArrayOfdouble2[paramInt2 + paramInt1 - k];
      double d2 = paramArrayOfdouble2[paramInt2 + k];
      double d3 = paramArrayOfdouble1[b] - paramArrayOfdouble1[m];
      double d4 = paramArrayOfdouble1[b + 1] + paramArrayOfdouble1[m + 1];
      double d5 = d1 * d3 + d2 * d4;
      d2 = d1 * d4 - d2 * d3;
      paramArrayOfdouble1[b] = paramArrayOfdouble1[b] - d5;
      paramArrayOfdouble1[b + 1] = d2 - paramArrayOfdouble1[b + 1];
      paramArrayOfdouble1[m] = paramArrayOfdouble1[m] + d5;
      paramArrayOfdouble1[m + 1] = d2 - paramArrayOfdouble1[m + 1];
    } 
  }
  
  private void rftfsub(double[] paramArrayOfdouble1, int paramInt1, double[] paramArrayOfdouble2, int paramInt2) {
    int i = this.n >> 1;
    int j = paramInt1 * 2 / i;
    int k = 0;
    for (byte b = 2;; b += 2) {
      if (b >= i)
        return; 
      int m = this.n - b;
      k += j;
      double d1 = 0.5D - paramArrayOfdouble2[paramInt2 + paramInt1 - k];
      double d2 = paramArrayOfdouble2[paramInt2 + k];
      double d3 = paramArrayOfdouble1[b] - paramArrayOfdouble1[m];
      double d4 = paramArrayOfdouble1[b + 1] + paramArrayOfdouble1[m + 1];
      double d5 = d1 * d3 - d2 * d4;
      d3 = d1 * d4 + d2 * d3;
      paramArrayOfdouble1[b] = paramArrayOfdouble1[b] - d5;
      int n = b + 1;
      paramArrayOfdouble1[n] = paramArrayOfdouble1[n] - d3;
      paramArrayOfdouble1[m] = paramArrayOfdouble1[m] + d5;
      paramArrayOfdouble1[++m] = paramArrayOfdouble1[m] - d3;
    } 
  }
  
  void makect(int paramInt1, double[] paramArrayOfdouble, int paramInt2) {
    this.ip[1] = paramInt1;
    if (paramInt1 > 1) {
      int i = paramInt1 >> 1;
      double d = Math.atan(1.0D) / i;
      paramArrayOfdouble[paramInt2 + 0] = Math.cos(i * d);
      paramArrayOfdouble[paramInt2 + i] = paramArrayOfdouble[paramInt2 + 0] * 0.5D;
      byte b = 1;
      while (true) {
        if (b < i) {
          paramArrayOfdouble[paramInt2 + b] = Math.cos(b * d) * 0.5D;
          paramArrayOfdouble[paramInt2 + paramInt1 - b] = Math.sin(b * d) * 0.5D;
          b++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void rdft(int paramInt, double[] paramArrayOfdouble) {
    int i = this.ip[0];
    int j = i;
    if (this.n > i << 2) {
      j = this.n >> 2;
      makewt(j);
    } 
    int k = this.ip[1];
    i = k;
    if (this.n > k << 2) {
      i = this.n >> 2;
      makect(i, this.w, j);
    } 
    if (paramInt >= 0) {
      if (this.n > 4) {
        bitrv2(this.n, paramArrayOfdouble);
        cftfsub(paramArrayOfdouble);
        rftfsub(paramArrayOfdouble, i, this.w, j);
      } else if (this.n == 4) {
        cftfsub(paramArrayOfdouble);
      } 
      double d1 = paramArrayOfdouble[0];
      double d2 = paramArrayOfdouble[1];
      paramArrayOfdouble[0] = paramArrayOfdouble[0] + paramArrayOfdouble[1];
      paramArrayOfdouble[1] = d1 - d2;
      return;
    } 
    paramArrayOfdouble[1] = 0.5D * (paramArrayOfdouble[0] - paramArrayOfdouble[1]);
    paramArrayOfdouble[0] = paramArrayOfdouble[0] - paramArrayOfdouble[1];
    if (this.n > 4) {
      rftbsub(paramArrayOfdouble, i, this.w, j);
      bitrv2(this.n, paramArrayOfdouble);
      cftbsub(paramArrayOfdouble);
      return;
    } 
    if (this.n == 4)
      cftfsub(paramArrayOfdouble); 
  }
}


/* Location:              C:\Users\Daisuke\Desktop\Channel_equalizer.jar!\jp\gr\java_conf\burstech\channel_equalizer\FFT4g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */