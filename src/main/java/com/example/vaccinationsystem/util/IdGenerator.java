package com.example.vaccinationsystem.util;

public final class IdGenerator {
    private IdGenerator() {
    }

    public static String nextPrefixedId(String prefix, String lastId) {
        if (prefix == null || prefix.isBlank()) {
            throw new IllegalArgumentException("prefix is required");
        }
        if (lastId == null || lastId.isBlank()) {
            return prefix + "000";
        }

        String normalized = lastId.trim();
        if (!normalized.startsWith(prefix)) {
            throw new IllegalArgumentException("lastId must start with prefix. prefix=" + prefix + ", lastId=" + lastId);
        }

        int width = normalized.length() - prefix.length();
        String numericPart = normalized.substring(prefix.length());
        int lastNumber = Integer.parseInt(numericPart);
        int nextNumber = lastNumber + 1;

        String nextStr = String.valueOf(nextNumber);
        int padCount = Math.max(0, width - nextStr.length());
        return prefix + "0".repeat(padCount) + nextStr;
    }
}

