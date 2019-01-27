package cd35;
import java.util.ArrayList;
import java.util.List;



public class tempMain {
	enum DasekiKekka{
		OUT__,
		HIT_1,
		HIT_2,
		HIT_3,
		HMRUN;
	}

	static class PitcherInfo{
		double bougyoritsu;
		double hidaritsu;
		PitcherInfo(double d)
		{
			this.bougyoritsu = d;
			hidaritsu = 0.0099*d*d - 0.039* d + 0.2604;
		}
	}

	static class ButterInfo{
		ButterInfo(double d)
		{
			daritsu = d;
		}
		double daritsu;
		ArrayList<DasekiKekka> dasekiKekkas = new ArrayList<DasekiKekka>();
	}

	static final double HONRUIDA = 0.116939891;
	static final double HIT_3 = HONRUIDA + 0.02431694;
	static final double HIT_2 = HIT_3 + 0.169672131;

	public static void main(String[] args)
	{
		List<PitcherInfo> pitchers = new ArrayList<PitcherInfo>();

		pitchers.add(new PitcherInfo(2.14));
		pitchers.add(new PitcherInfo(2.45));
		pitchers.add(new PitcherInfo(2.62));
		pitchers.add(new PitcherInfo(2.99));
		pitchers.add(new PitcherInfo(3.11));
		pitchers.add(new PitcherInfo(3.63));
		pitchers.add(new PitcherInfo(3.68));
		pitchers.add(new PitcherInfo(4.03));




		for(int i = 0 ; i < pitchers.size(); i++)
		{
			int ten[] = new int[9];
			int hit1Sum = 0;
			int hit2Sum = 0;
			int hit3Sum = 0;
			int honSum = 0;
			int dajun = 0;

			List<ButterInfo> butterInfos = new ArrayList<ButterInfo>();

			butterInfos.add(new ButterInfo(0.251)); // 神里
			butterInfos.add(new ButterInfo(0.310)); // ソト
			butterInfos.add(new ButterInfo(0.318)); // 宮崎
			butterInfos.add(new ButterInfo(0.295)); // 筒香
			butterInfos.add(new ButterInfo(0.288)); // ロペス
			butterInfos.add(new ButterInfo(0.268)); // 梶谷
			butterInfos.add(new ButterInfo(0.244)); // 大和
			butterInfos.add(new ButterInfo(0.280)); // 関根
			butterInfos.add(new ButterInfo(0.261)); // 桑原

			for(int j = 0 ; j < 9 ; j++){
				int out = 0;

				boolean[] rui = new boolean[3];
				while(out < 3)
				{
					ButterInfo butter = butterInfos.get(dajun);
					// rand
					double r = Math.random();
					if( r < (pitchers.get(i).hidaritsu /2 + butter.daritsu/2 ))
					{
						// Hit
						// 塁だ判定
						double r2 = Math.random();
						if(r2 < HONRUIDA)
						{
							for(int k = 0 ; k < rui.length ; k++)
							{
								if(rui[k])
								{
									ten[j]++;
									rui[k] = false;
								}
							}

							ten[j]++;
							honSum++;
							butter.dasekiKekkas.add(DasekiKekka.HMRUN);
						}
						else if(r2 < HIT_3)
						{

							for(int k = 0 ; k < rui.length ; k++)
							{
								if(rui[k])
								{
									ten[j]++;
									rui[k] = false;
								}
							}

							rui[2] = true;
							hit3Sum++;
							butter.dasekiKekkas.add(DasekiKekka.HIT_3);
						}
						else if(r2 < HIT_2)
						{
							for(int k = 2 ; k >= 1 ; k--)
							{
								if(rui[k])
								{
									ten[j]++;
									rui[k] = false;
								}
							}

							if(rui[0])
							{
								rui[2] = true;
								rui[0] = false;
							}

							rui[1] = true;
							hit2Sum++;
							butter.dasekiKekkas.add(DasekiKekka.HIT_2);
						}
						else
						{
							if(rui[2])
							{
								ten[j]++;
								rui[2] = false;
							}

							for(int k = 1 ; k >= 0 ; k--)
							{
								if(rui[k])
								{
									rui[k+1] = true;;
									rui[k] = false;
								}
							}

							rui[0] = true;
							hit1Sum++;
							butter.dasekiKekkas.add(DasekiKekka.HIT_1);
						}

					}
					else
					{
						butter.dasekiKekkas.add(DasekiKekka.OUT__);
						// Out
						out++;
					}
					dajun++;
					dajun = dajun % 9;
				}


				System.out.printf("%d, ", ten[j]);
			}
			int tenSum = 0;
			for(int tmp : ten)
			{
				tenSum += tmp;
			}
			// ゲームセット
			System.out.printf("%d, %d, %d, %d, %d",tenSum, hit1Sum, hit2Sum, hit3Sum, honSum);
			System.out.println();

			for( ButterInfo b : butterInfos )
			{

				for(DasekiKekka d : b.dasekiKekkas)
				{
				    System.out.printf("%s  ", d.name());
				}
				System.out.println();
			}
		}



	}


}
