package DecisionTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DecisionTree {
	private String[][] data;
	private String[] attrNames;
	private String[] results;
	private Map<String, Set<String>> attrMap;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecisionTree tree = new DecisionTree();
		String[][] data = {
				{"age","income","xin","result"},
				{"T","H","G","Y"},
				{"T","L","B","Y"},
				{"M","M","G","Y"},
				{"M","M","G","N"},
				{"T","H","B","Y"},
				{"M","L","B","N"},
				{"T","M","G","Y"}
		};
		tree.setData(data);
		double result = tree.computeGain(data, "income");
		System.out.println(result);
	}

	public void setData(String[][] data) {
		this.data = data;
		this.initValue();
	}

	private void initValue() {
		this.attrNames = data[0];
		int attrNum = this.attrNames.length;
		int len = data.length;
		Set<String> set = new HashSet<String>();
		for(int i = 1; i < len; i++) {
			set.add(data[i][attrNum - 1]);
		}
		this.results = new String[set.size()];
		set.toArray(this.results);
		this.attrMap = new HashMap<String, Set<String>>();
		for(int j = 0; j < attrNum; j++) {
			Set<String> attrSet = new HashSet<String>();
			for(int i = 1; i < len; i++) {
				attrSet.add(data[i][j]);
			}
			this.attrMap.put(attrNames[j], attrSet);
		}
	}
	
	public double computeEntropy(String[][] rData, String attrName, String attrValue, boolean isParent) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for(int i = 0; i < results.length; i++) {
			countMap.put(results[i], 0);
		}
		int attrNum = attrNames.length;
		int allCount = 0;
		for(int j = 0; j < attrNames.length; j++) {
			if(attrNames[j].equals(attrName)) {
				for(int i = 1; i < rData.length; i++) {
					if(isParent || (!isParent && rData[i][j].equals(attrValue))) {
						String result = rData[i][attrNum - 1];
						int count = countMap.get(result);
						countMap.put(result, ++count);
						allCount++;
					}
				}
			}
		}
		double result = 0;
		Iterator<Map.Entry<String, Integer>> iterator = countMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			int count = entry.getValue();
			if(count == 0 || count == allCount) {
				result += 0;
				continue;
			}
			double p = (double) count / allCount;
			result += -1 * p * Math.log(p) / Math.log(2.0);
		}
		return result;
	}
	
	public double computeGain(String[][] data, String attrName) {
		int attrNum = this.attrNames.length;
		int allCount = data.length - 1;
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		Set<String> attrSet = this.attrMap.get(attrName);
		for(String attr : attrSet) {
			countMap.put(attr, 0);
		}
		for(int j = 0; j < attrNum; j++) {
			if(attrNames[j].equals(attrName)) {
				for(int i = 1; i <= allCount; i++) {
					int count = countMap.get(data[i][j]);
					countMap.put(data[i][j], ++count);
				}
			}
		}
		double allEntropy = this.computeEntropy(data, attrName, null, true);
		double childEntropy = 0;
		for(String attr : attrSet) {
			double p = (double)countMap.get(attr) / allCount;
			if(p > 0) {
				childEntropy += p * this.computeEntropy(data, attrName, attr, false);
			}
		}
		return allEntropy - childEntropy;
	}

}
