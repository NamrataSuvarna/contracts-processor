package home.contracts.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import home.contracts.processor.model.Contract;
import home.contracts.processor.util.Utility;

public class ContractsProcessor {

	private static Map<String, Double> computeFinalPriceShares(List<Contract> contracts) {
		Map<String, Double> finalContractPriceShares = new HashMap<>();
		for (Contract contract : contracts) {
			if (finalContractPriceShares.containsKey(contract.getBusinessUnit())) {
				double actualPriceShare = finalContractPriceShares.get(contract.getBusinessUnit())
						+ contract.getActualPriceShare();
				finalContractPriceShares.put(contract.getBusinessUnit(), actualPriceShare);
			} else {
				finalContractPriceShares.put(contract.getBusinessUnit(), contract.getActualPriceShare());
			}
		}
		return finalContractPriceShares;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(computeFinalPriceShares(Utility
				.getContracts("src\\main\\resources\\contracts", "2020-03-31")));
	}

}
