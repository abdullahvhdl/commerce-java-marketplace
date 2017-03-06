package com.respodo.commerce.service.csvparser;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.respodo.commerce.dto.csv.StoreCSVDTO;
import com.respodo.commerce.repository.dao.AddressRepository;
import com.respodo.commerce.repository.dao.ImageRepository;
import com.respodo.commerce.repository.dao.StoreRepository;
import com.respodo.commerce.repository.dao.StoreTypeRepository;
import com.respodo.commerce.repository.dao.UserRepository;
import com.respodo.commerce.repository.domain.Address;
import com.respodo.commerce.repository.domain.Image;
import com.respodo.commerce.repository.domain.Store;
import com.respodo.commerce.repository.domain.StoreType;
import com.respodo.commerce.repository.domain.User;

@Component
public class StoreCSVParser implements CSVParser {

	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private StoreTypeRepository storeTypeRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired 
	private UserRepository userRepository;

	@Override
	public void parseCSVFile(CSVReader reader) throws IOException {
		String[] header2 = reader.readNext();
		CsvToBean<StoreCSVDTO> csv = new CsvToBean<StoreCSVDTO>();
		ColumnPositionMappingStrategy<StoreCSVDTO> strategy = new ColumnPositionMappingStrategy<StoreCSVDTO>();
		strategy.setType(StoreCSVDTO.class);
		String[] columns = header2;
		strategy.setColumnMapping(columns);

		List<StoreCSVDTO> list = csv.parse(strategy, reader);
		for (StoreCSVDTO bean : list) {
			Store store = storeRepository.findOneByUniqueId(bean.getUniqueId());
			if (store == null) {
				store = new Store();
				store.setDescription(bean.getDescription());
				store.setName(bean.getName());
				store.setUniqueId(bean.getUniqueId());
				
				StoreType storeType = storeTypeRepository.findOneByType(bean.getStoreType());
				store.setStoreType(storeType);

				Address address = new Address();
				address.setAddress1(bean.getAddress1());
				address.setAddress2(bean.getAddress2());
				address.setAddressType(bean.getAddressType());
				address.setCity(bean.getCity());
				address.setZipCode(bean.getZipCode());
				address.setCountry(bean.getCountry());
				address.setState(bean.getState());
				store.setAddress(address);

				Image image = new Image();
				image.setImage1(bean.getImage1());
				image.setImage2(bean.getImage2());
				image.setImage3(bean.getImage3());
				image.setImage4(bean.getImage4());
				image.setOtherImages(bean.getOtherImages());
				image.setThumbnail(bean.getThumbnail());
				store.setImage(image);
				User user=userRepository.findOneByLogin(bean.getOwnerUserName());
				if(user !=null){
					store.setOwner(user);
				}
				addressRepository.save(address);
				imageRepository.save(image);
				storeRepository.save(store);

			} else {
				
				Address address = store.getAddress();
				Image image = store.getImage();
				if (address == null) {
					address = new Address();
				}
				if (image == null) {
					image = new Image();
				}
				if (bean.getName() != null && !bean.getName().isEmpty()) {
					store.setName(bean.getName());
				}
				if (bean.getDescription() != null
						&& !bean.getDescription().isEmpty()) {
					store.setDescription(bean.getDescription());
				}
				if (bean.getStoreType() != null
						&& !bean.getStoreType().equals(0)) {
					StoreType storeType = storeTypeRepository.findOneByType(bean.getStoreType());
					store.setStoreType(storeType);
				}

				if (bean.getAddress1() != null && !bean.getAddress1().isEmpty()) {
					address.setAddress1(bean.getAddress1());

				}
				if (bean.getAddress2() != null && !bean.getAddress2().isEmpty()) {
					address.setAddress2(bean.getAddress2());

				}
				if (bean.getAddressType() != null
						&& !bean.getAddressType().isEmpty()) {
					address.setAddressType(bean.getAddressType());

				}
				if (bean.getCity() != null && !bean.getCity().isEmpty()) {
					address.setCity(bean.getCity());
				}
				if (bean.getZipCode() != null && !bean.getZipCode().isEmpty()) {
					address.setZipCode(bean.getZipCode());

				}
				if (bean.getCountry() != null && !bean.getCountry().isEmpty()) {
					address.setCountry(bean.getCountry());

				}
				if (bean.getState() != null && !bean.getState().isEmpty()) {
					address.setState(bean.getState());

				}
				if (bean.getImage1() != null && !bean.getImage1().isEmpty()) {
					image.setImage1(bean.getImage1());

				}
				if (bean.getImage2() != null && !bean.getImage2().isEmpty()) {
					image.setImage2(bean.getImage2());

				}
				if (bean.getImage3() != null && !bean.getImage3().isEmpty()) {
					image.setImage3(bean.getImage3());

				}
				if (bean.getImage4() != null && !bean.getImage4().isEmpty()) {
					image.setImage4(bean.getImage4());

				}
				if (bean.getOtherImages() != null
						&& !bean.getOtherImages().isEmpty()) {
					image.setThumbnail(bean.getThumbnail());

				}
				if (bean.getThumbnail() != null
						&& !bean.getThumbnail().isEmpty()) {
					store.setImage(image);

				}
				User user=userRepository.findOneByLogin(bean.getOwnerUserName());
				if(user !=null){
					store.setOwner(user);
				}
				store.setAddress(address);
				store.setImage(image);
				
				addressRepository.save(address);
				imageRepository.save(image);
				storeRepository.save(store);
			}
		}
	}

	@Override
	public void parseCSVFileForStore(Long storeId, CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseCSVFileForCatalog(Long storeId, Long catalogId,
			CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

}
