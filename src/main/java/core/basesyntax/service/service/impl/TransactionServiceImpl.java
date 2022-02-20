package core.basesyntax.service.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.TransactionService;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {
    public static final String COLUMN_NAMES_LINE = "type,fruit,quantity";
    public static final String COMA_SEPARATOR = ",";
    public static final int OPERATION_INDEX = 0;
    public static final int FRUIT_INDEX = 1;
    public static final int QUANTITY_INDEX = 2;

    @Override
    public List<FruitTransaction> creatListTransaction(List<String> dataFromFile) {
        if (dataFromFile == null || dataFromFile.isEmpty()) {
            throw new RuntimeException("Data from input file can`t be empty");
        }
        dataFromFile.remove(COLUMN_NAMES_LINE);
        return dataFromFile
                .stream()
                .map(this::createTransaction)
                .collect(Collectors.toList());
    }

    private FruitTransaction createTransaction(String data) {
        FruitTransaction fruitTransaction = new FruitTransaction();
        String[] line = data.split(COMA_SEPARATOR);
        String operation = line[OPERATION_INDEX].replaceAll("\\W", "");
        String fruit = line[FRUIT_INDEX].replaceAll("\\W", "");
        String quantity = line[QUANTITY_INDEX].replaceAll("\\D", "");
        checkTransaction(operation, fruit, quantity);
        fruitTransaction.setOperation(fruitTransaction.getOperationLetter(operation));
        fruitTransaction.setFruit(fruit);
        fruitTransaction.setQuantity(Integer.parseInt(quantity));
        return fruitTransaction;
    }

    private void checkTransaction(String operation, String fruit, String quantity) {
        if (operation.equals("")) {
            throw new RuntimeException("There isn`t activity type.");
        }
        if (fruit.equals("")) {
            throw new RuntimeException("There isn`t fruit type.");
        }
        if (quantity.equals("")) {
            throw new RuntimeException("There isn`t quantity value.");
        }
    }
}


