package com.sams.inventorymanagement.dto;

public class ItemWithSupplierDTO {
    private Long itemId;
    private String itemName;
    private String itemShortDescription;
    private Double itemPrice;
    private Integer itemQuantity;
    private String CategoryName;
    private String supplierName;
    private String supplierAddress;
    private String supplierEmail;
    private String supplierPhone;
}

//    @GetMapping("/with-supplier-info")
//    public List<ItemWithSupplierDTO> getItemsWithSupplierInfo() {
//        List<Item> items = itemService.getAllItems(); // You'll need to implement this method
//
//        return items.stream()
//                .map(item -> {
//                    ItemWithSupplierDTO dto = new ItemWithSupplierDTO();
//                    dto.setItemId(item.getId());
//                    dto.setItemName(item.getName());
//                    dto.setItemShortDescription(item.getShortDescription());
//                    dto.setItemPrice(item.getPrice());
//                    dto.setItemQuantity(item.getQuantity());
//
//                    Supplier supplier = item.getCategory().getSupplier();
//                    dto.setSupplierName(supplier.getName());
//                    dto.setSupplierAddress(supplier.getAddress());
//                    dto.setSupplierEmail(supplier.getEmail());
//                    dto.setSupplierPhone(supplier.getPhone());
//
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
