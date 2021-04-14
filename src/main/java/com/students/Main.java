package com.students;

import com.students.operation.OperationType;
import com.students.operation.Operations;
import com.students.operation.OperationsImpl;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Operations operations = new OperationsImpl();
        Scanner scanner = new Scanner(System.in);

        OperationType operationType;

        do {
            System.out.println("Choose operation from: " + Arrays.toString(OperationType.values()));
            String opName = scanner.nextLine();
            operationType = OperationType.findOperation(opName);

            if (operationType == null) {
                System.out.println("Unknown operation!");
            }

            switch (operationType) {
                case INSERT_INTO:
                    operations.insertInto();
                    break;
                case GET_ALL:
                    operations.getAll();
                    break;
                case GET_BY_ID:
                    operations.getById();
                    break;
                case DELETE:
                    operations.delete();
                    break;
                case DELETE_BY_ID:
                    operations.deleteById();
                    break;
                case EXIT:
                    operations.exit();
                    break;
            }
        } while (operationType != OperationType.EXIT);
    }
}
