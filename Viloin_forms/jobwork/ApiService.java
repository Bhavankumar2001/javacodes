package com.altrocks.forms.jobwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.altrocks.forms.utils.APIConstants;
import com.altrocks.forms.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class ApiService {

	@Autowired
	UtilsService Utils;

	@Description(value = "A_PurchaseOrderScheduleLine API")
	@Async
	public CompletableFuture<List<PurchaseOrderScheduleLinePropertiesDAO>> getPurchaseOrderScheduleLineAsync()
			throws JsonMappingException, JsonProcessingException, UnirestException, ParseException,
			InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.jobWork.A_PurchaseOrderScheduleLine;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<PurchaseOrderScheduleLinePropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<PurchaseOrderScheduleLinePropertiesDAO>> future = CompletableFuture
					.supplyAsync(() -> {
						try {
							// System.out.println("Calling API: " + apiUrl);
							return getPurchaseOrderScheduleLine(apiUrl); // Replace this with your actual API call logic
						} catch (Exception e) {
							e.printStackTrace(); // Log the exception
							return Collections.emptyList(); // Return an empty list in case of failure
						}
					});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<PurchaseOrderScheduleLinePropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<PurchaseOrderScheduleLinePropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	@Description(value = "A_PurchaseOrderScheduleLine List API Consume")
	public List<PurchaseOrderScheduleLinePropertiesDAO> getPurchaseOrderScheduleLine(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<PurchaseOrderScheduleLinePropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<PurchaseOrderScheduleLineDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class,
							PurchaseOrderScheduleLineDAO.class));

			List<PurchaseOrderScheduleLinePropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "POSubcontractingComponent API")
	@Async
	public CompletableFuture<List<POSubcontractingComponentPropertiesDAO>> getPOSubcontractingComponentAsync()
			throws JsonMappingException, JsonProcessingException, UnirestException, ParseException,
			InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.jobWork.POSubcontractingComponent;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<POSubcontractingComponentPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<POSubcontractingComponentPropertiesDAO>> future = CompletableFuture
					.supplyAsync(() -> {
						try {
							// System.out.println("Calling API: " + apiUrl);
							return getPOSubcontractingComponent(apiUrl); // Replace this with your actual API call logic
						} catch (Exception e) {
							e.printStackTrace(); // Log the exception
							return Collections.emptyList(); // Return an empty list in case of failure
						}
					});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<POSubcontractingComponentPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<POSubcontractingComponentPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	@Description(value = "A_POSubcontractingComponent List API Consume")
	public List<POSubcontractingComponentPropertiesDAO> getPOSubcontractingComponent(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<POSubcontractingComponentPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<POSubcontractingComponentDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class,
							POSubcontractingComponentDAO.class));

			List<POSubcontractingComponentPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "A_ProductPlant API")
	@Async
	public CompletableFuture<List<ProductPlantPropertiesDAO>> getAProductPlantAsync() throws JsonMappingException,
			JsonProcessingException, UnirestException, ParseException, InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.jobWork.A_ProductPlant;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<ProductPlantPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<ProductPlantPropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getProductPlant(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<ProductPlantPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<ProductPlantPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	@Description(value = "A_ProductPlant List API Consume")
	public List<ProductPlantPropertiesDAO> getProductPlant(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<ProductPlantPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<ProductPlantDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, ProductPlantDAO.class));

			List<ProductPlantPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "A_PurchaseOrderNote API")
	@Async
	public CompletableFuture<List<PurchaseOrderNotePropertiesDAO>> getPurchaseOrderNoteAsync()
			throws JsonMappingException, JsonProcessingException, UnirestException, ParseException,
			InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.jobWork.A_PurchaseOrderNote;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<PurchaseOrderNotePropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<PurchaseOrderNotePropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getPurchaseOrderNote(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<PurchaseOrderNotePropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<PurchaseOrderNotePropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	@Description(value = "A_PurchaseOrderNote List API Consume")
	public List<PurchaseOrderNotePropertiesDAO> getPurchaseOrderNote(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<PurchaseOrderNotePropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<PurchaseOrderNoteDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, PurchaseOrderNoteDAO.class));

			List<PurchaseOrderNotePropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "PlantAddress API")
	@Async
	public CompletableFuture<List<PlantAddressPropertiesDAO>> getPlantAddressAsync() throws JsonMappingException,
			JsonProcessingException, UnirestException, ParseException, InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.debitNote.YY1_plantaddr;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<PlantAddressPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<PlantAddressPropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getPlantAddress(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<PlantAddressPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<PlantAddressPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnirestException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Description(value = "SupplierInvoiceTax List API Consume")
	public List<PlantAddressPropertiesDAO> getPlantAddress(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<PlantAddressPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<PlantAddressDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, PlantAddressDAO.class));

			List<PlantAddressPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Async
	public CompletableFuture<List<purchaseOrderPropertiesDAO>> getPurchaseOrderAsync() throws JsonMappingException,
			JsonProcessingException, UnirestException, ParseException, InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.debitNote.A_PurchaseOrder;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<purchaseOrderPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl =  API + "?$skip=" + skipValue + "&$top=" + pageSize;
			// Asynchronously call the API for the current skip value
			CompletableFuture<List<purchaseOrderPropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getPurchaseOrder(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<purchaseOrderPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<purchaseOrderPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnirestException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Description(value = "PurchaseOrder List API Consume")
	public List<purchaseOrderPropertiesDAO> getPurchaseOrder(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<purchaseOrderPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<purchaseOrderDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, purchaseOrderDAO.class));

			List<purchaseOrderPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());
//System.out.println("data ::"+ data);
			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "supplierAddress API")
	@Async
	public CompletableFuture<List<supplierAddressPropertiesDAO>> getSupplierAddressAsync() throws JsonMappingException,
			JsonProcessingException, UnirestException, ParseException, InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.debitNote.YY1_SupplierAddress;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<supplierAddressPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<supplierAddressPropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getSupplierAddress(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<supplierAddressPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<supplierAddressPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnirestException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Description(value = "SupplierAddress List API Consume")
	public List<supplierAddressPropertiesDAO> getSupplierAddress(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<supplierAddressPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<supplierAddressHeaderDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, supplierAddressHeaderDAO.class));

			List<supplierAddressPropertiesDAO> data = entryNodesValues.stream().map(e -> e.getContent().getProperties())
					.collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

	@Description(value = "purchaseOrderItem API")
	@Async
	public CompletableFuture<List<purchaseOrderItemPropertiesDAO>> getPurchaseOrderItemAsync()
			throws JsonMappingException, JsonProcessingException, UnirestException, ParseException,
			InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.debitNote.A_PurchaseOrderItem;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<purchaseOrderItemPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API
					+ "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<purchaseOrderItemPropertiesDAO>> future = CompletableFuture.supplyAsync(() -> {
				try {
					// System.out.println("Calling API: " + apiUrl);
					return getPurchaseOrderItem(apiUrl); // Replace this with your actual API call logic
				} catch (Exception e) {
					e.printStackTrace(); // Log the exception
					return Collections.emptyList(); // Return an empty list in case of failure
				}
			});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<purchaseOrderItemPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<purchaseOrderItemPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnirestException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Description(value = "purchaseOrderItem List API Consume")
	public List<purchaseOrderItemPropertiesDAO> getPurchaseOrderItem(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<purchaseOrderItemPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<purchaseOrderItemDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, purchaseOrderItemDAO.class));

			List<purchaseOrderItemPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());
//			System.out.println("data ::" + data);
			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}
	
	
	@Description(value = "Valuation API")
	@Async
	public CompletableFuture<List<ProductValuationPropertiesDAO>> getProductValuationAsync()
			throws JsonMappingException, JsonProcessingException, UnirestException, ParseException,
			InterruptedException, ExecutionException {

		String API = "https://" + Utils.port + "-" + APIConstants.MRINReport.AProductValuation;

		int pageSize = 3000;
		int pageNumber = 0;
		int fetchedDataSize = 0; // Variable to track the number of records fetched in each call
		List<CompletableFuture<List<ProductValuationPropertiesDAO>>> futures = new ArrayList<>();

		// Use a while loop to keep calling the API until no more data is fetched
		do {
			// Calculate the skip value for pagination
			int skipValue = pageNumber * pageSize;
			String apiUrl = API + "?$skip=" + skipValue + "&$top=" + pageSize;

			// Asynchronously call the API for the current skip value
			CompletableFuture<List<ProductValuationPropertiesDAO>> future = CompletableFuture
					.supplyAsync(() -> {
						try {
							// System.out.println("Calling API: " + apiUrl);
							return getProductValuation(apiUrl); // Replace this with your actual API call logic
						} catch (Exception e) {
							e.printStackTrace(); // Log the exception
							return Collections.emptyList(); // Return an empty list in case of failure
						}
					});

			fetchedDataSize = future.get().size();
			// Add the future to the list of futures
			futures.add(future);

			// Increment the page number for the next iteration
			pageNumber++;

			// We should not use `future.join()` here since we want to keep the calls
			// asynchronous
			// We assume fetchedDataSize will be set by the API call in future.get()

			// System.out.println("fetchedDataSize ==>"+fetchedDataSize);

		} while (fetchedDataSize > 0); // Continue fetching until no more records are returned

		// Combine all the futures into one CompletableFuture
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allFutures.thenApply(v -> {
			ArrayList<ProductValuationPropertiesDAO> cummulativeList = new ArrayList<>();
			for (CompletableFuture<List<ProductValuationPropertiesDAO>> future : futures) {
				try {
					cummulativeList.addAll(future.get()); // Get the result of each future
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			}
			return cummulativeList;
		});

		// Collect all the results after all futures complete
	}

	@Description(value = "ProductValuation List API Consume")
	public List<ProductValuationPropertiesDAO> getProductValuation(String url)
			throws UnirestException, IOException, ParseException {

		ArrayList<ProductValuationPropertiesDAO> arrayList = new ArrayList<>();

		try {

			HttpResponse<String> jsonresponse = Utils.ApiCall(url);

			JsonNode getJsonValues = Utils.XmlToJsonConversionTestContent(jsonresponse.getBody());

			ObjectMapper objectMapper = new ObjectMapper();

			List<ProductValuationDAO> entryNodesValues = objectMapper.readValue(getJsonValues.traverse(),
					objectMapper.getTypeFactory().constructCollectionType(List.class,
							ProductValuationDAO.class));

			List<ProductValuationPropertiesDAO> data = entryNodesValues.stream()
					.map(e -> e.getContentDAO().getPropertiesDAO()).collect(Collectors.toList());

			arrayList.addAll(data);
			return data;
		} catch (Exception e2) {
			System.out.println(e2.getLocalizedMessage());
			// TODO: handle exception
		}

		return arrayList;
	}

}
